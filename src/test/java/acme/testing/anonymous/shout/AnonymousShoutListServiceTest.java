
package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListServiceTest extends AcmePlannerTest {

	/**
	 * This test achieves a 82.9% coverage in the AnonymousShoutListService.
	 * 
	 * Test 1:
	 * In this test we check there is the list of shouts, that can be empty or fulfilled.
	 *
	 * Test 2:
	 * We check that we can't list shouts login as an administrator.
	 */
	
	
	/**
	 * In this test we check there is the list of shouts, that can be empty or fulfilled.
	 * 
	 */
	@Test
	public void positiveShoutListing() {
		super.navigateHome();
		super.clickAndGo(By.linkText("Anonymous"));
		super.clickAndGo(By.linkText("List shouts"));
		Assertions.assertTrue(super.exists(By.cssSelector(".control.sorting_1")) || super.exists(By.className("dataTables_empty")));
	}

	/**
	 * We check that we can't list shouts login as an administrator.
	 * 
	 */
	@Test
	public void negativeShoutListing() {

		super.signIn("administrator", "administrator");
 		super.driver.get("http://localhost:8080/Acme-Planner/anonymous/shout/list");
 		this.checkPanicExists();
		this.signOut();

	}
}
