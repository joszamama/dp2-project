
package acme.testing.anonymous.tasks;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskShowServiceTest extends AcmePlannerTest {

	/**
	 * This test achieves a 79.0% coverage in the AnonymousTaskShowService.
	 * 
	 * Test 1:
	 * In this test we check that the selected task is the same that it shows when we click it in our service.
	 * 
	 * Test 2:
	 * We check that we can't show tasks logged as an administrator.
	 * 
	 */

	/**
	 * 
	 * In this test we check that the selected task is the same that it shows when we click it in our service.
	 * 
	 */

	@Test
	public void anonymousTaskShow() {

		super.navigateHome();
		super.clickOnMenu("Anonymous", "List unfinished public tasks");

		final String title = super.locateOne(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[2]")).getText();
		final String startDate = super.locateOne(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[3]")).getText();
		final String endDate = super.locateOne(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[4]")).getText();
		final String workLoad = super.locateOne(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[5]")).getText();

		super.click((By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[2]")));

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("executionStart", startDate);
		super.checkInputBoxHasValue("executionEnd", endDate);
		super.checkInputBoxHasValue("workloadParsed", workLoad);

	}
	
	/**
	 * 
	 * We check that we can't show tasks logged as an administrator.
	 * 
	 */

	@Test
	public void negativeTaskShowing() {

		super.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/anonymous/task/show?id=23");
		this.checkPanicExists();
		this.signOut();
	}

}
