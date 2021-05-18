
package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

/**
 * These tests achieve a 45.3% coverage in the AdministratorConfigurationCreateService.
 * The reasons it doesn't achieve a 100% is because the assert
 * statements that assert that the framework is working properly and
 * also because of the unbind() method which isn't used when executing
 * any of the actions and thus can't be covered by the tests, bringing
 * coverage down. A solution would be to remove the method, but since it's
 * an abstract method and thus an implementation must be provided, we've
 * decided to leave the implementation in.
 * 
 * Test 1:
 * Log in as administrator, access the configuration and update it with
 * legal values, enter the configuration page again and check that the
 * changes are persisted.
 * 
 * Test 2:
 * Log in as administrator, access the configuration and update it with
 * illegal values and check that an error is thrown.
 * This violates the constraint that threshold values must be between 0
 * (including) and 1 (including) by sending values under 0 and values
 * over 1.
 * 
 */
public class AdministratorConfigurationCreateServiceTest extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------

	/**
	 * Log in as administrator, access the configuration and update it with
	 * legal values, enter the configuration page again and check that the
	 * changes are persisted.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
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
	 * Log in as administrator, access the configuration and update it with
	 * illegal values and check that an error is thrown.
	 * This violates the constraint that threshold values must be between 0
	 * (including) and 1 (including) by sending values under 0 and values
	 * over 1.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
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
