
package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

/**
 * These tests achieve a 71.4% coverage in the AdministratorConfigurationShowService.
 * The only reason they don't achieve a 100% is the assert statements that
 * assert that the framework is working properly.
 * 
 * Test 1:
 * Log in as administrator, access the configuration, check that no errors
 * appear.
 * 
 * Test 2:
 * Don't log in as administrator, access the configuration, check that an error
 * appears.
 *
 */
public class AdministratorConfigurationShowServiceTest extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------

	/**
	 * Log in as administrator, access the configuration, check that no errors
	 * appear.
	 */
	@Test
	public void positive() {
		this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/configuration/show");
		super.checkNotErrorsExist();
		this.signOut();
	}

	/**
	 * Don't log in as administrator, access the configuration, check that an error
	 * appears.
	 */
	@Test
	public void negative() {
		//this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/configuration/show");
		super.checkErrorsExist();
		//this.signOut();
	}

}
