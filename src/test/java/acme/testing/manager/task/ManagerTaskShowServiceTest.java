
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

/**
 * These tests achieve a 6.2% coverage in the ManagerTaskShowService.
 * We are investigating why this is happening and have already reported it to the tutor.
 * 
 * Test 1:
 * First of all, we login as a manager. Then, we access the list task function and
 * click on a row to show the task information. Finally we check that every attribute has a proper value.
 * 
 * Test 2:
 * First, we login as manager1 and access the list task function. Then, we click on the first row and get the current URL.
 * Secondly, we logout. To finish, we try to access to the URL that we get before and a panich should appear.
 *
 */
public class ManagerTaskShowServiceTest extends AcmePlannerTest {

	/**
	 * First of all, we login as a manager. Then, we access the list task function and
	 * click on a row to show the task information. Finally we check that every attribute has a proper value.
	 * 
	 * @param title
	 * @param description
	 * @param link
	 * @param executionStart
	 * @param executionEnd
	 * @param workload
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/show-tasks.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveShow(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List my tasks");

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("executionStart", executionStart);
		super.checkInputBoxHasValue("executionEnd", executionEnd);
		super.checkInputBoxHasValue("workloadParsed", workload);
		super.signOut();
	}

	/**
	 * First, we login as manager1 and access the list task function. Then, we click on the first row and get the current URL.
	 * Secondly, we logout. To finish, we try to access to the URL that we get before and a panich should appear.
	 * The constraint that is violated is that an Anonymous cannot show a task of a Manager.
	 */
	@Test
	@Order(20)
	public void negativeShow() {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List my tasks");
		super.clickOnListingRecord(0);
		final String url = this.getCurrentUrl();
		super.signOut();
		super.navigate(url, url);
		super.checkPanicExists();
	}
}
