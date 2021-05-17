package acme.testing.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskListServiceTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/tasks/manager/list-tasks.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAndShowTasks(final int recordIndex, final String title, final String executionStart, final String executionEnd, final String workload) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List my tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, executionStart);
		super.checkColumnHasValue(recordIndex, 2, executionEnd);
		super.checkColumnHasValue(recordIndex, 3, workload);
	}
}
