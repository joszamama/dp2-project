/*
 * AnonymousShoutCreateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.parameters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorParametersCreateService implements AbstractCreateService<Administrator, Spam> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorParametersRepository repository;

	// AbstractCreateService<Administrator, Shout> interface --------------

	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;
		
		boolean result;
		Spam spam;
		final int spamId;

		
		spam = this.repository.findSpamParameters().iterator().next();
		result = spam != null;

		return true;
	}

	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "wordList", "threshold");
		model.setAttribute("spamId", entity.getId());
	}
	
	
	@Override
	public Spam instantiate(final Request<Spam> request) {
		assert request != null;

		final Spam result = this.repository.findSpamParameters().iterator().next();

		return result;
	}
	

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Spam> request, final Spam entity) throws Exception {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
		
		
	}
	
	public String regexCompilator(final String wordList) {
		String regex = "(";
		final String[] spamWords = wordList.split(",");
		
		for(int i = 0; i < spamWords.length ; i++) {
			
			if(i != spamWords.length-1) { //si no es la ultima palabra, se pone un | 
				final String word = spamWords[i].trim();
				for(int j = 0; j < word.length(); j++) {
					if(word.charAt(j) == ' ') {
						regex += word.charAt(j);
					}else {
						regex += word.charAt(j) + "+";
					}
				}
				regex += "|";
			}else { //si es la última palabra, no se pone un |
				final String word = spamWords[i].trim();
				for(int j = 0; j < word.length(); j++) {
					if(word.charAt(j) == ' ') {
						regex += word.charAt(j);
					}else {
						regex += word.charAt(j) + "+";
					}
				}
			}
		}
		regex += ")";
		System.out.println("Regex: " + regex);

		return regex.trim();
	}
	
	public boolean isSpam(final String message) {
		boolean res = false;
		final Spam parameters = this.repository.findSpamParameters().iterator().next();
		 final Pattern p = Pattern.compile(this.regexCompilator(parameters.getWordList()));
	     final Matcher m = p.matcher(message);
	     double numSpamWords = 0.0;
	     while(m.find()) {
	         System.out.println(m.group());
	         numSpamWords++;
	     }
	     System.out.println("Numero de palabras spam: "+ numSpamWords);
	     if(numSpamWords !=0 ) {
	    	 final int numTotalWords = message.trim().split("\\s+").length;
	    	 System.out.println("Numero de palabras total: " + numTotalWords);
	    	 final Double similarity = numSpamWords / numTotalWords;
	    	 System.out.println("Similaridad: " + similarity);
	    	 
	    	 System.out.println("Umbral: " + parameters.getThreshold());
	    	 if(similarity >= parameters.getThreshold()) {
	    		 res = true;
	    		 System.out.println("ES SPAM");
	    	 }else {
	    		 System.out.println("Hay spam pero no lo suficiente");
	    	 }
	     }else {
	    	 System.out.println("No spam detectado");
	     }
	     return res;
	}


}
