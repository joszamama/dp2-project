
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
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list-shouts.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveShoutListing(final int recordIndex,final String moment,final String author,final String text, final String info) {
		// super.navigateHome();
		//super.clickOnMenu("Anonymous","List shouts");
		// Assertions.assertTrue(super.exists(By.cssSelector(".control.sorting_1")) || super.exists(By.className("dataTables_empty")));
		// super.click(By.xpath("//*[@id=\"list\"]/thead/tr/th[2]"));

		//super.clickOnMenu("Anonymous", "List shouts");
		//super.click(By.xpath("//*[@id=\"list\"]/thead/tr/th[2]"));
			
			super.clickOnMenu("Anonymous", "List shouts");
			
			super.checkColumnHasValue(recordIndex, 0, moment);
			super.checkColumnHasValue(recordIndex, 1, author);
			super.checkColumnHasValue(recordIndex, 2, text);
	}
			
	

	/**
	 * We check that we can't list shouts logged as an administrator.
	 * 
	 */
	@Test
	public void negativeShoutListing() {

		super.signIn("administrator", "administrator");
 		super.driver.get("http://localhost:8080/Acme-Planner/anonymous/shout/list");
 		this.checkPanicExists();
		this.signOut();

	}
}
