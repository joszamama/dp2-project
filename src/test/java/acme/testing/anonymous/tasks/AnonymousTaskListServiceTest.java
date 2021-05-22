package acme.testing.anonymous.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListServiceTest extends AcmePlannerTest{
	
	/**
	 * This test achieves a 75.0% coverage in the AnonymousShoutListService.
	 * 
	 * Test 1:
	 * As in the anonymous shout listing test, in this one we check there is a list of the tasks, that can be
	 * empty or filled.
	 *
	 */
	
	
	/**
	 * As in the anonymous shout listing test, in this one we check there is a list of the tasks, that can be
	 * empty or filled.
	 */
	@Test
	public void positiveTaskListing() {
		// List unfinished public tasks 
		super.navigateHome();
		super.clickAndGo(By.linkText("Anonymous"));
		super.clickAndGo(By.linkText("List unfinished public tasks"));
		Assertions.assertTrue(super.exists(By.cssSelector(".control.sorting_1")) || super.exists(By.className("dataTables_empty")));
	}
		
}
