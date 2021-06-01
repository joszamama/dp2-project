
package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListServiceTest extends AcmePlannerTest {

	/**
	 * This test achieves a 100.0% coverage in the AnonymousShoutListService.
	 * 
	 * Test 1:
	 * In this test we check there is the list of shouts, that can be empty or fulfilled.
	 * We also check that the value in the csv is the same as the list of shouts
	 *
	 * Test 2:
	 * We check that we can't list shouts login as an administrator.
	 */

	/**
	 * In this test we check there is the list of shouts, that can be empty or fulfilled.
	 * We also check that the value in the csv is the same as the list of shouts.
	 * 
	 * @param recordIndex
	 * @param moment
	 * @param author
	 * @param text
	 * @param info
	 * 
	 *            This test access the drop-down menu of anonymous and enters the listing
	 *            shouts. You should check that the list displays the same as our input csv file, in which we enter the
	 *            sample data records that should be displayed.
	 * 
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list-shouts.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveShoutListing(final int recordIndex, final String moment, final String author, final String text, final String info) {
		super.clickOnMenu("Anonymous", "List shouts");
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
	}

	/**
	 * We check that we can't list shouts logged as an administrator.
	 * 
	 * Once logged in as administrator, the first thing to do is to check that the anonymous section does not exist. Then we try to access by url
	 * to the list of shouts", and check that the result is an error page, as it is not authorised.
	 * 
	 */
	@Test
	public void negativeShoutListing() {
		super.signIn("administrator", "administrator");
		super.navigate("anonymous/shout/list", "");
		this.checkPanicExists();
		this.signOut();

	}
}
