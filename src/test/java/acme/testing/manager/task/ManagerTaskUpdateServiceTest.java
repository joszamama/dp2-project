package acme.testing.manager.task;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateServiceTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/tasks/manager/update-tasks-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void UpdatePositive(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
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
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/tasks/manager/update-tasks-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void UpdateNegative(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
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
		super.checkErrorsExist();
	}
}
