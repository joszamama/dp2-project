
package acme.testing.authenticated.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerUpdateServiceTest extends AcmePlannerTest {

	/**
	 * Sign up as regular user, become a manager and sign out, will be used for both tests
	 */
	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();
		super.signUp("testusername", "testtest", "testname", "testsurname", "testemail@acme.com");
		super.signIn("testusername", "testtest");
		super.clickOnMenu("Account", "Become a manager");
		super.fillInputBoxIn("company", "initial_value_company");
		super.fillInputBoxIn("sector", "initial_value_department");
		super.clickOnSubmitButton("Register");
		super.checkSimplePath("/master/welcome");
		super.signOut();
	}
	
	/**
	 * Sign in with a manager account and try to change data for manager profile
	 * with values from the file provided
	 * 
	 * @param recordIndex
	 * @param company
	 * @param department
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/update-manager-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateManagerPositive(final int recordIndex, final String company, final String department) {
		super.signIn("testusername", "testtest");
		super.clickOnMenu("Account", "Manager data");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", department);
		super.clickOnSubmitButton("Update");

		super.checkSimplePath("/master/welcome");
		super.clickOnMenu("Account", "Manager data");
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("sector", department);
		super.signOut();
	}

	/**
	 * Sign in with a manager account and try to change data for manager profile
	 * Try to set company and department values to blank and null
	 * 
	 * @param recordIndex
	 * @param company
	 * @param department
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/update-manager-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateManagerNegative(final int recordIndex, final String company, final String department) {
		super.signIn("testusername", "testtest");
		super.clickOnMenu("Account", "Manager data");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", department);
		super.clickOnSubmitButton("Update");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", department);
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();
		super.signOut();
	}
	
}
