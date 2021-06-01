
package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerUpdateServiceTest extends AcmePlannerTest {

	/**
	 * Sign in with a manager account and try to change data for manager profile
	 * with values from the file provided
	 * 
	 * @param recordIndex
	 * @param company
	 * @param department
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/manager/create-and-update-manager-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateManagerPositive(final int recordIndex, final String company, final String department) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Account", "Manager data");
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", department);
		super.clickOnSubmitButton("Update");

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
	@CsvFileSource(resources = "/authenticated/manager/create-and-update-manager-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateManagerNegative(final int recordIndex, final String company, final String department) {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Account", "Manager data");

		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("sector", department);
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();
		super.signOut();
	}

}
