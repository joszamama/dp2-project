package acme.testing;

import org.junit.jupiter.api.Test;

public class AdministratorDashboardTest extends AcmePlannerTest {

	// Test cases -------------------------------------------------------------

	@Test
	public void positiveDashboard() {
		this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboard/show");
		super.checkNotErrorsExist();
		this.signOut();
	}

	@Test
	public void negativeDashboard() {
		//this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboard/show");
		super.checkErrorsExist();
		//this.signOut();
	}

}
