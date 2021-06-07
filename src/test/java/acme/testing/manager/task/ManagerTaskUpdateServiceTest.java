
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

/**
 * These tests achieve a 73.0% coverage in the ManagerTaskUpdateService.
 * 
 * Test 1:
 * First of all, we login as a manager. Then, we access the list task function and
 * click on a row to show the task information. We update the values of the attributes with valid data and we click on "Update" button.
 * No errors should appear in the form.
 * 
 * Test 2:
 * First of all, we login as a manager. Then, we access the list task function and
 * click on a row to show the task information. We update the values of the attributes with data that vialote some constraints.
 * Next we click on "Update" button and should appear error messages in the form.
 *
 */
public class ManagerTaskUpdateServiceTest extends AcmePlannerTest {

	/**
	 * * First of all, we login as a manager. Then, we access the list task function and
	 * click on a row to show the task information. We update the values of the attributes with valid data and we click on "Update" button.
	 * No errors should appear in the form.
	 * 
	 * @param title
	 * @param description
	 * @param link
	 * @param executionStart
	 * @param executionEnd
	 * @param workload
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-tasks-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List my tasks");

		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("workloadParsed", workload);

		super.clickOnSubmitButton("Update");
		super.checkNotErrorsExist();
		
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
	 * First of all, we login as a manager. Then, we access the list task function and
	 * click on a row to show the task information. We update the values of the attributes with data that vialote some constraints.
	 * Next we click on "Update" button and should appear error messages in the form.
	 * The constraint that are violated are the followings:
	 * The length of the title must be between 1 and 80 and cannot be blank
	 * The executionStart and the executionEnd cannot be null
	 * The executionStart and the executionEnd must be future dates
	 * The executionStart must be before executionEnd
	 * The workload cannot be null and follow the pattern HH:mm
	 * Workload minutes cannot be higher than 59 and lower than 00
	 * Workload have to be in consonance with the executionStart and the executionEnd
	 * The description cannot be null and the length of the description must be between 1 and 500
	 * The link must be a valid URL
	 * 
	 * @param title
	 * @param description
	 * @param link
	 * @param executionStart
	 * @param executionEnd
	 * @param workload
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-tasks-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateNegative(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List my tasks");

		super.clickOnListingRecord(1);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("workloadParsed", workload);

		super.clickOnSubmitButton("Update");
		super.checkErrorsExist();
		super.signOut();
	}
}
