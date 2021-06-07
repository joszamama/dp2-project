
package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

/**
 * These tests achieve a 40.2% coverage in the ManagerTaskDeleteService.
 * The reasons it doesn't achieve a 100% is because the assert
 * statements that assert that the framework is working properly and
 * also because of the unbind() and bind() methods which aren't used when executing
 * any of the actions and thus can't be covered by the tests, bringing
 * coverage down. A solution would be to remove the methods, but they are
 * abstract methods and thus an implementation must be provided, we've
 * decided to leave the implementation in.
 * 
 * Test 1:
 * First of all, we login as a manager. Then, access the list task function, click on a row to show the information of a task
 * and then click on the button "Delete". Next, we access the list task function again and there shouldn't exist any panic.
 * 
 * Test 2:
 * First of all, we login as a manager1. Then, access the list task function, click on a row to show the information of a task
 * and then click on the button "Delete". Next, we access the list task function again and there shouldn't exist any panic.
 *
 */
public class ManagerTaskDeleteServiceTest extends AcmePlannerTest {

	/**
	 * First of all, we login as a manager. Then, access the list task function, click on a row to show the information of a task
	 * and then click on the button "Delete". Next, we access the list task function again and there shouldn't exist any panic.
	 */

	@Test
	@Order(10)
	public void deletePositive() {
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List my tasks");
		super.clickOnListingRecord(0);
		super.clickOnSubmitButton("Delete");
		super.clickOnMenu("Manager", "List my tasks");
		super.checkNotPanicExists();
		super.signOut();
	}
	/**
	 * First of all, we login as a manager1. Then, access the list task function, click on a row to show the information of a task
	 * and then click on the button "Delete". Next, we access the list task function again and there shouldn't exist any panic.
	 * The constraint that is trying to violated is that manager1 cannot delete tasks created by manager2.
	 */
	@Test
	@Order(20)
	public void deleteNegative() {
		super.signIn("manager2", "manager2");
		super.clickOnMenu("Manager", "List my tasks");
		super.clickOnListingRecord(0);
		final String url = this.getCurrentUrl();
		super.signOut();
		super.signIn("manager1", "manager1");
		super.navigate(url , "");
		super.checkPanicExists();
		super.signOut();
	}
}
