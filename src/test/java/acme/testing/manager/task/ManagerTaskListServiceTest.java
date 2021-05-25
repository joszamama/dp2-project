
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

/**
 * These tests achieve a 78.2% coverage in the ManagerTaskListService.
 * 
 * Test 1:
 * First of all, we login as a manager. Then, we access the list task function and
 * check that each column has a proper value.
 * 
 * Test 2:
 * We try to access to the list task function of a Manager without being authenticated. Hence a panic should appear.
 *
 */
public class ManagerTaskListServiceTest extends AcmePlannerTest {

	/**
	 * First of all, we login as a manager. Then, we access the list task function and
	 * check that each column has a proper value.
	 * 
	 * @param title
	 * @param description
	 * @param link
	 * @param executionStart
	 * @param executionEnd
	 * @param workload
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list-tasks.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveList(final int recordIndex, final String title, final String executionStart, final String executionEnd, final String workload) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List my tasks");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, executionStart);
		super.checkColumnHasValue(recordIndex, 2, executionEnd);
		super.checkColumnHasValue(recordIndex, 3, workload);
	}

	/**
	 * We try to access to the list task function of a Manager without being authenticated. Hence a panic should appear. The constraint that is
	 * violated is that an Anonymous cannot list the tasks of a Manager
	 */
	@Test
	@Order(20)
	public void negativeList() {
		this.driver.get("http://localhost:8080/Acme-Planner/manager/task/list");
		super.checkPanicExists();
	}
}
