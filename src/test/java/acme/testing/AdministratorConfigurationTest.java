package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

/**
 * These tests achieve a 56.4% coverage.
 * The only reason they don't achieve a 100% is the assert statements that
 * assert that the framework is working properly as well as the unbind()
 * method in the class AdministratorConfigurationCreateService which isn't
 * called during the execution of any of the actions.
 * 
 * Test 1:
 * Log in as administrator, access the configuration and update it,
 * enter the configuration again and check that the changes are persisted.
 * 
 * Test 2
 * Don't log in as administrator, access the configuration, check that an error
 * appears
 *
 */
public class AdministratorConfigurationTest extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------

	/**
	 * In this test, we check that we can update the configuration as a logged in administrator and that the changes are persisted.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveSetCustomizationParameters(final String wordList, final String threshold) {
		this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/configuration/show");
		super.fill(By.id("wordList"), wordList);
		super.fill(By.id("threshold"), threshold);
		super.clickOnSubmitButton("Change parameters");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/configuration/show");
		super.checkInputBoxHasValue("wordList", wordList);
		super.checkInputBoxHasValue("threshold", threshold);
		this.signOut();
	}

	/**
	 * In this test, we check that an error appears when accessing the configuration when we aren't logged in as an administrator.
	 */
	@Test
	public void negativeSetCustomizationParameters() {
		//this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/configuration/show");
		super.checkErrorsExist();
		//this.signOut();
	}

}
