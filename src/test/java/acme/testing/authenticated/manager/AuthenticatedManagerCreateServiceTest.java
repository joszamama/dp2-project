
package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerCreateServiceTest extends AcmePlannerTest {

	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/create-manager-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void becomeManagerPositive(final int recordIndex, final String company, final String department) {
		super.signUp("user_positive"+recordIndex, "testtest", "testname", "testsurname", "testemail@acme.com");
		super.signIn("user_positive"+recordIndex, "testtest");
		super.clickOnMenu("Account", "Become a manager");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", department);
		super.clickOnSubmitButton("Register");

		super.checkSimplePath("/master/welcome");
		super.clickOnMenu("Account", "Manager data");
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("sector", department);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/create-manager-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void becomeManagerNegative(final int recordIndex, final String company, final String department) {	
		if(recordIndex==0) {
			super.signUp("user_negative", "testtest", "testname", "testsurname", "testemail@acme.com");	
		}
		super.signIn("user_negative", "testtest");
		super.clickOnMenu("Account", "Become a manager");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", department);
		super.clickOnSubmitButton("Register");

		super.checkErrorsExist();
	}

}
