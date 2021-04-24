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

package acme.features.anonymous.shout;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousShoutRepository repository;

	// AbstractCreateService<Administrator, Shout> interface --------------

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "info");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Shout();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);
		result.setInfo("http://example.org");

		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final Boolean isSpam = this.isSpam((entity.getAuthor()+ " " + entity.getText()).toLowerCase());
		
		//LevenshteinDistance levenshteinDistance = new LevenshteinDistance(10);
		//System.out.println("Porcentaje similaridad: " + new JaroWinklerSimilarity().apply(entity.getText(), entity.getAuthor()));
		//final Double diff =  1.0 - new JaroWinklerSimilarity().apply(entity.getText(), entity.getAuthor());
		//System.out.println("Porcentaje que difiere (100 - lo de arriba): " + diff);
		
		errors.state(request, !isSpam, "*", "manager.task.form.error.spam-detected");

	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) throws Exception {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		final boolean isSpam = this.isSpam((entity.getAuthor()+ " " + entity.getText()).toLowerCase());
		if(isSpam == false) {
			this.repository.save(entity);
		}else {
			System.out.println("SPAM: " + entity.getText());
			System.out.println("Mensaje borrado");
		}
		
		
	}
	
	public boolean isSpam(final String message) {
		boolean res = false;
		 final Pattern p = Pattern.compile("(s+e+x+o+|s+e+x+|h+a+r+d+ c+o+r+e+|e+x+t+r+e+m+o+|v+i+a+g+r+a+|c+i+a+l+i+s+|n+i+g+e+r+i+a|y+o+u+'+v+e+ w+o+n+|h+a+s+ g+a+n+a+d+o+|m+i+l+l+i+o+n+ d+o+l+l+a+r+|m+i+l+l+ó+n+ d+e+ d+o+l+a+r+e+s+)");
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
	    	 
	    	 if(similarity >= 0.1) {
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
	
	//public boolean isSpam(final String message) {
		/*
		boolean res;
		if(mensaje.toLowerCase().matches("(.*)(s+e+x+o+|s+e+x+|h+a+r+d+ c+o+r+e+|e+x+t+r+e+m+o+|v+i+a+g+r+a+|c+i+a+l+i+s+|n+i+g+e+r+i+a|y+o+u+'+v+e+ w+o+n+|h+a+s+ g+a+n+a+d+o+|m+i+l+l+i+o+n+ d+o+l+l+a+r+|m+i+l+l+ó+n+ d+e+ d+o+l+a+r+e+s+)(.*)")) {
			res = true;
		}else {
			res = false;
		}
		*/
	
	/*
		final boolean res;
		double acum = 0.0;
		final List<String> list = Arrays.asList("sexo", "sex", "hard core", "extremo", "viagra", "cialis", "nigeria", "you've won", "has ganado", "million dollar", "millón de dólares");
		for(final String word: list) {
			acum += new JaroWinklerSimilarity().apply(word, message);
		}
		System.out.println("Similaridad acumulada: " + acum);
		System.out.println("Diff: " + (1.0 - acum));
		if(acum  <= 0.1) {
            res = true;
        }else {
        	res = false;
        }
		
		return res;
	}
	
	*/

}
