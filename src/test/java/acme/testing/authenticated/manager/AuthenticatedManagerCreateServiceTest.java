
package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerCreateServiceTest extends AcmePlannerTest {

	/**
	 * Sign up as user, use different user for each test. Try to become manager with data provided
	 * 
	 * @param recordIndex
	 * @param company
	 * @param department
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/create-and-update-manager-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void becomeManagerPositive(final int recordIndex, final String company, final String department) {
		super.signUp("user_positive" + recordIndex, "testtest", "testname", "testsurname", "testemail@acme.com");
		super.signIn("user_positive" + recordIndex, "testtest");
		super.clickOnMenu("Account", "Become a manager");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", department);
		super.clickOnSubmitButton("Register");

		super.clickOnMenu("Account", "Manager data");
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("sector", department);

		super.signOut();
	}

	/**
	 * Sign up, sign in and input faulty data for each test to try to become manager
	 * Try to set company and department values to blank and null
	 * 
	 * @param recordIndex
	 * @param company
	 * @param department
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/create-and-update-manager-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void becomeManagerNegative(final int recordIndex, final String company, final String department) {
		super.signUp("user_negative" + recordIndex, "testtest", "testname", "testsurname", "testemail@acme.com");
		super.signIn("user_negative" + recordIndex, "testtest");

		super.clickOnMenu("Account", "Become a manager");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", department);
		super.clickOnSubmitButton("Register");

		super.checkErrorsExist();
		super.signOut();
	}

}
