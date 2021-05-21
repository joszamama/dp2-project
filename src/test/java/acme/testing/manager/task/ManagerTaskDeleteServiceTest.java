package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteServiceTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-tasks.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void deleteTasks(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List my tasks");
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("Delete");
		super.clickOnMenu("Manager", "List my tasks");
	}
}
