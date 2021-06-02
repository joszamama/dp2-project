
package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskShowServiceTest extends AcmePlannerTest {

	/**
	 * 
	 * Coverage: 79.0%
	 * 
	 * First of all, we login as an administrator. Then, we access the authenticated list of tasks (finished and public)
	 * and then we click on each tasks checking that the value is correct.
	 * We are testing the feature that shows the detais of a finished public task by an Authenticated principal.
	 * 
	 * @param title
	 * @param description
	 * @param link
	 * @param executionStart
	 * @param executionEnd
	 * @param workload
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/show-finished-public.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listFinishedPublic(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated", "List finished public tasks");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, executionStart);
		super.checkColumnHasValue(recordIndex, 2, executionEnd);
		super.checkColumnHasValue(recordIndex, 3, workload);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("executionStart", executionStart);
		super.checkInputBoxHasValue("executionEnd", executionEnd);
		super.checkInputBoxHasValue("workloadParsed", workload);

		this.signOut();
	}

	/**
	 * We check that we can't access a task if not signed in as an auth user.
	 * We check this by accessing a task as an authenticated user,
	 * getting the URL of that task, signing off and then
	 * trying to access the URL of the task.
	 * We are violating the constraint of authorization.
	 */

	/**
	 * We check that we can't list finished and public tasks as an anonymous user.
	 * We are violating the constraint of authorization.
	 */
	@Test
	public void negativeTaskListing() {
		super.navigate("/authenticated/task/list", "");
		this.checkPanicExists();
	}

}
