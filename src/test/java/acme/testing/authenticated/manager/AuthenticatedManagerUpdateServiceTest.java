
package acme.testing.authenticated.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerUpdateServiceTest extends AcmePlannerTest {

	/**
	 * 
	 * 66.3%
	 * 
	 * Sign up and become manager
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
	 * Try to change data for manager profile
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
	 * Try to change data for manager profile (data is faulty)
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
