package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListServiceTest extends AcmePlannerTest {
	
	/**
	 * First of all, we login as an administrator. Then, we access the authenticated list of tasks (finished and public)
	 * We are testing the feature to list finished public tasks as an Authenticated principal
	 * 
	 * @param title
	 * @param description
	 * @param link
	 * @param executionStart
	 * @param executionEnd
	 * @param workload
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list-finished-public.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveListFinishedPublic(final int recordIndex, final String title, final String executionStart, final String executionEnd, final String workload) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated", "List finished public tasks");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, executionStart);
		super.checkColumnHasValue(recordIndex, 2, executionEnd);
		super.checkColumnHasValue(recordIndex, 3, workload);

		this.signOut();
	}
	/**
	 * 
	 * We check that we can't list finished and public tasks as an anonymous user.
	 * We are violating the constraint of authorization.
	 */

	@Test
	public void negativeTaskListing() {
		super.driver.get("http://localhost:8080/Acme-Planner/authenticated/task/list");
		this.checkPanicExists();
	}
}
