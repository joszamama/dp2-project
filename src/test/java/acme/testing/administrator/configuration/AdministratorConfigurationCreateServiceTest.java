
package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

/**
 * These tests achieve a ??.?% coverage in the AdministratorConfigurationCreateService.
 * The only reason they don't achieve a 100% is the assert statements that
 * assert that the framework is working properly.
 * 
 * Test 1:
 * Log in as administrator, access the configuration and update it with
 * legal values, enter the configuration page again and check that the
 * changes are persisted.
 * 
 * Test 2:
 * Log in as administrator, access the configuration and update it with
 * illegal values and check that an error is thrown.
 * 
 */
public class AdministratorConfigurationCreateServiceTest extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------

	/**
	 * In this test, we check that we can update the configuration as a logged in administrator and that the changes are persisted.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final String wordList, final String threshold) {
		this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/configuration/show");
		super.fill(By.id("wordList"), wordList);
		super.fill(By.id("threshold"), threshold);
		super.clickOnSubmitButton("Change parameters");
		super.checkNotErrorsExist();
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/configuration/show");
		super.checkInputBoxHasValue("wordList", wordList);
		super.checkInputBoxHasValue("threshold", threshold);
		this.signOut();
	}

	/**
	 * In this test, we check that we can't update the configuration with invalid threshold values.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negative(final String wordList, final String threshold) {
		this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/configuration/show");
		super.fill(By.id("wordList"), wordList);
		super.fill(By.id("threshold"), threshold);
		super.clickOnSubmitButton("Change parameters");
		super.checkErrorsExist();
		this.signOut();
	}

}
