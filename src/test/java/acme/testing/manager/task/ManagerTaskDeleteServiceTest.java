package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteServiceTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-tasks.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String link, final String workload) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List my tasks");
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("Delete");
		super.clickOnMenu("Manager", "List my tasks");
		super.checkNotPanicExists();
	}
	
	@Test
	@Order(10)
	public void deleteNegative() {
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "List my tasks");
		this.driver.get("http://localhost:8080/Acme-Planner/manager/task/show?id=27");
		super.signOut();
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List my tasks");
		this.driver.get("http://localhost:8080/Acme-Planner/manager/task/show?id=27");
		super.checkPanicExists();
	}
}
