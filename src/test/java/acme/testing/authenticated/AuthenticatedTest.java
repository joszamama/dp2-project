
package acme.testing.authenticated;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/tasks/authenticated/list-finished-public.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void becomeManagerPositive() {
		super.signUp("testusername", "testtest", "testname", "testsurname", "testemail@acme.com");
		super.signIn("testusername", "testtest");
		super.clickOnMenu("Account", "Become a manager");
		
		super.fillInputBoxIn("company", "test company 1");
		super.fillInputBoxIn("sector", "testing department");
		super.clickOnSubmitButton("Register");
		
		super.checkSimplePath("/master/welcome");
		super.clickOnMenu("Account", "Manager data");
		super.checkInputBoxHasValue("company", "test company 1");
		super.checkInputBoxHasValue("sector", "testing department");
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/tasks/authenticated/list-finished-public.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
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
}
