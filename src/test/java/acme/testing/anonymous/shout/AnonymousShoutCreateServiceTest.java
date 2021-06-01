
package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateServiceTest extends AcmePlannerTest {

	/**
	 * These tests achieve a 96.1% coverage in the AnonymousShoutCreateService.
	 * 
	 * Test 1:
	 * Access the create shout function, create a new positive case of a shout and check that the moment of the
	 * creation of the shout is less than 3 minutes before now. That means it is the same shout that we have
	 * created.
	 * 
	 * Test 2:
	 * In this case, we create a shout with some "spam" words in the text and we check that we aren´t
	 * allowed to create it because the spam error appeared. We also check bad url's, authors with less than 5 letters.
	 *
	 */

	/**
	 * Access the create shout function, create a new positive case of a shout and check that no errors exist.
	 * 
	 * @param author
	 * @param text
	 * @param info
	 * 
	 * 
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreate(final String author, final String text, final String info) {

		super.clickOnMenu("Anonymous", "Create shout");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);

		super.clickOnSubmitButton("Shout!");
		super.checkNotErrorsExist();
	}

	/**
	 * In this case, we create a shout with some "spam" words in the text and we check that we aren´t
	 * allowed to create it because the spam error appeared.
	 * We also check bad url's, authors with less than 5 letters.
	 * 
	 * @param author
	 * @param text
	 * @param info
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeCreate(final String author, final String text, final String info) {
		super.clickOnMenu("Anonymous", "Create shout");

		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);

		super.clickOnSubmitButton("Shout!");
		super.checkErrorsExist();
	}
}
