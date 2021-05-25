
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

/**
 * These tests achieve a 74.6% coverage in the ManagerTaskCreateService.
 * 
 * Test 1:
 * First of all, we login as a manager. Then, access the create task function, create a new positive case of a task and
 * check that there is no error in the form.
 * 
 * Test 2:
 * First of all, we login as a manager. Then, we create a task trying to violate different constraints in the form and we check that we aren´t
 * allowed to create it because the errors appeared.
 *
 */
public class ManagerTaskCreateServiceTest extends AcmePlannerTest {

	/**
	 * First of all, we login as a manager. Then, access the create task function, create a new positive case of a task and
	 * check that there is no error in the form.
	 * 
	 * @param title
	 * @param description
	 * @param link
	 * @param executionStart
	 * @param executionEnd
	 * @param workload
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-tasks-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "Create a task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("workloadParsed", workload);

		super.clickOnSubmitButton("Create");
		super.checkNotErrorsExist();
	}

	/**
	 * First of all, we login as a manager. Then, we create a task trying to violate different constraints in the form and we check that we aren´t
	 * allowed to create it because the errors appeared.
	 * 
	 * @param title
	 * @param description
	 * @param link
	 * @param executionStart
	 * @param executionEnd
	 * @param workload
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-tasks-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "Create a task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("workloadParsed", workload);

		super.clickOnSubmitButton("Create");
		super.checkErrorsExist();
	}
}
