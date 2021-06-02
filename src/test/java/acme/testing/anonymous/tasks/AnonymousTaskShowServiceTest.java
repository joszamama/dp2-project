
package acme.testing.anonymous.tasks;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskShowServiceTest extends AcmePlannerTest {

	/**
	 * This test achieves a 79.0% coverage in the AnonymousTaskShowService.
	 * 
	 * Test 1:
	 * In this test we check that the selected task is the same that it shows when we click it in our service.
	 * 
	 * Test 2:
	 * We check that we can't show tasks logged as an administrator.
	 * 
	 */

	/**
	 * 
	 * In this test we check that the selected task is
	 * the same that it shows when we click it in our service.
	 * 
	 * We check that the variables title, start date, end date and workload
	 * are the same in both the list and show pages.
	 * 
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/show-tasks.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void anonymousTaskShow(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "List unfinished public tasks");

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

	}

	/**
	 * 
	 * We check that we can't show tasks logged as an administrator.
	 * Once logged in as administrator, the first thing to do is to check that the anonymous section does not exist. Then we try to access by url
	 * to the list of tasks, and check that the result is an error page, as it is not authorised.
	 * 
	 */

	@Test
	@Order(20)
	public void negativeTaskShowing() {

		super.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/anonymous/task/show?id=23");
		this.checkPanicExists();
		this.signOut();
	}

}
