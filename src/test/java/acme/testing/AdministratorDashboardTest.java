
package acme.testing;

import org.junit.jupiter.api.Test;

/**
 * These tests achieve a 94.5% coverage.
 * The only reason they don't achieve a 100% is the assert statements that
 * assert that the framework is working properly.
 * 
 * Test 1:
 * Log in as administrator, access the dashboard, check that no errors appear
 * 
 * Test 2:
 * Log in as administrator, click "Populate DB (initial)" so that there's no
 * data for the dashboard, access the dashboard, check that no errors appear
 * 
 * Test 3:
 * Don't log in as administrator, access the dashboard, check that an error
 * appears
 *
 */
public class AdministratorDashboardTest extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------

	/**
	 * In this test, we check that no errors appear when accessing a dashboard full of data as a logged administrator.
	 */
	@Test
	public void positiveDashboard() {
		this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboard/show");
		super.checkNotErrorsExist();
		this.signOut();
	}

	/**
	 * In this test, we check that no errors appear when accessing a dashboard without data as a logged administrator.
	 * 
	 * We do this by populating the database with only the initial data.
	 */
	@Test
	public void positiveDashboardWithoutData() {
		this.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Populate DB (initial)");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboard/show");
		super.checkNotErrorsExist();
		this.signOut();
	}

	/**
	 * In this test, we check that an error appears when accessing a dashboard when we aren't logged in as an administrator.
	 */
	@Test
	public void negativeDashboard() {
		//this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboard/show");
		super.checkErrorsExist();
		//this.signOut();
	}

}
