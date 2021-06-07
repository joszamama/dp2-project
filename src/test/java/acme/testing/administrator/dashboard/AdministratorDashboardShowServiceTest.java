
package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

/**
 * These tests achieve a 94.5% coverage in the AdministratorDashboardShowService.
 * The only reason they don't achieve a 100% is the assert statements that
 * assert that the framework is working properly.
 * 
 * Test 1:
 * Log in as administrator, access the dashboard, check that no errors
 * appear.
 * 
 * Test 2:
 * Log in as administrator, click "Populate DB (initial)" so that there's no
 * data for the dashboard, access the dashboard, check that no errors appear
 * even when there isn't data for the dashboard.
 * 
 * Test 3:
 * Don't log in as administrator, access the dashboard, check that an error
 * appears
 *
 */
public class AdministratorDashboardShowServiceTest extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------

	/**
	 * Log in as administrator, access the dashboard, check that no errors
	 * appear.
	 */
	@Test
	public void positiveAuthorised() {
		this.signIn("administrator", "administrator");
		super.navigate("/administrator/dashboard/show", "");
		super.checkNotPanicExists();
		this.signOut();
	}

	/**
	 * Log in as administrator, click "Populate DB (initial)" so that there's no
	 * data for the dashboard, access the dashboard, check that no errors appear
	 * even when there isn't data for the dashboard.
	 */
	@Test
	public void positiveNoData() {
		this.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Populate DB (initial)");
		super.navigate("/administrator/dashboard/show", "");
		super.checkNotPanicExists();
		super.clickOnMenu("Administrator", "Populate DB (samples)");
		super.checkNotPanicExists();
		this.signOut();
	}

	/**
	 * Don't log in as administrator, access the dashboard, check that an error
	 * appears
	 */
	@Test
	public void negativeUnauthorised() {
		super.navigate("/administrator/dashboard/show", "");
		super.checkPanicExists();
	}

}
