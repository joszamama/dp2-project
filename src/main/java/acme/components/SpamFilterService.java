
package acme.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.features.administrator.configuration.AdministratorConfigurationRepository;

@Service
public class SpamFilterService {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigurationRepository repository;


	public String regexCompilator(final String wordList) {
		final StringBuilder builder = new StringBuilder();
		builder.append("(");
		final String[] spamWords = wordList.split(",");

		for (int i = 0; i < spamWords.length; i++) {

			if (i != spamWords.length - 1) { //si no es la ultima palabra, se pone un | 
				final String word = spamWords[i].trim();
				for (int j = 0; j < word.length(); j++) {
					if (word.charAt(j) == ' ') {
						builder.append(word.charAt(j));
					} else {
						builder.append(word.charAt(j) + "+");
					}
				}
				builder.append("|");
			} else { //si es la última palabra, no se pone un |
				final String word = spamWords[i].trim();
				for (int j = 0; j < word.length(); j++) {
					if (word.charAt(j) == ' ') {
						builder.append(word.charAt(j));
					} else {
						builder.append(word.charAt(j) + "+");
					}
				}
			}
		}
		builder.append(")");
		System.out.println("Regex: " + builder.toString());

		return builder.toString().trim();
	}

	public boolean isSpam(final String... messages) {
		final Configuration configuration = this.repository.findConfiguration().iterator().next();
		boolean res = false;
		String fullMessage = String.join(" ", messages).toLowerCase();
		fullMessage = fullMessage.replaceAll("( )+", " "); //normalize spaces

		final String wordList = configuration.getWordList().toLowerCase();
		final Pattern p = Pattern.compile(this.regexCompilator(wordList));
		final Matcher m = p.matcher(fullMessage);
		double numSpamWords = 0.0;
		while (m.find()) {
			System.out.println(m.group());
			numSpamWords++;
		}
		System.out.println("Numero de palabras spam: " + numSpamWords);
		if (numSpamWords != 0) {
			final int numTotalWords = fullMessage.trim().split("\\s+").length;
			System.out.println("Numero de palabras total: " + numTotalWords);
			final Double similarity = numSpamWords / numTotalWords;
			System.out.println("Similaridad: " + similarity);
			System.out.println("Umbral: " + configuration.getThreshold());
			if (similarity >= configuration.getThreshold()) {
				res = true;
				System.out.println("ES SPAM");
			} else {
				System.out.println("Hay spam pero no lo suficiente");
			}
		} else {
			System.out.println("No spam detectado");
		}
		return res;
	}

}
