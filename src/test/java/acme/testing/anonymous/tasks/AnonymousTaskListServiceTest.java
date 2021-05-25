
package acme.testing.anonymous.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListServiceTest extends AcmePlannerTest {

	/**
	 * In this tests, we have an 89.1% of coverage of Anonymous Task List.
	 * 
	 * Test 1: As in the anonymous shout listing test, in this one we check there is a list of the tasks, that can be
	 * empty or filled. And must be the same than in the csv file
	 * 
	 * Test 2: In this test we check that we can't list tasks login as an administrator.
	 * 
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/show-tasks.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTaskListing(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
 {
		// List unfinished public tasks 
		super.navigateHome();
		super.clickOnMenu("Anonymous", "List unfinished public tasks");
		Assertions.assertTrue(super.exists(By.cssSelector(".control.sorting_1")) || super.exists(By.className("dataTables_empty")));
		
			super.clickOnListingRecord(recordIndex);
			
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("link", link);
			super.checkInputBoxHasValue("executionStart", executionStart);
			super.checkInputBoxHasValue("executionEnd", executionEnd);
			super.checkInputBoxHasValue("workloadParsed", workload);
		}
	}

	/**
	 * 
	 * We check that we can't list tasks login as an administrator.
	 * 
	 */

	@Test
	public void negativeTaskListing() {

		super.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/anonymous/task/list");
		this.checkPanicExists();
		this.signOut();
	}
}
