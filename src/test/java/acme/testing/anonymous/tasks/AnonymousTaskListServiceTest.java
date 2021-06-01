
package acme.testing.anonymous.tasks;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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

	/**
	 * As in the anonymous shout listing test, in this one we check there is a list of the tasks, that can be
	 * empty or filled. And must be the same than in the csv file
	 * 
	 * @param recordIndex
	 * @param title
	 * @param description
	 * @param executionStart
	 * @param executionEnd
	 * @param link
	 * @param workload
	 * 
	 *            We first check that the list exists with Assertions.assert(.control.sorting) and then
	 *            check that all the values match the ones we need.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/show-tasks.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTaskListing(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "List unfinished public tasks");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, executionStart);
		super.checkColumnHasValue(recordIndex, 2, executionEnd);
		super.checkColumnHasValue(recordIndex, 3, workload);

	}

	/**
	 * 
	 * We check that we can't list tasks logged as an administrator.
	 * 
	 * Once logged in as administrator, the first thing to do is to check that the anonymous section does not exist. Then we try to access by url
	 * to the list of tasks", and check that the result is an error page, as it is not authorised.
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
