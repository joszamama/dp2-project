
package acme.testing.anonymous.tasks;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskShowServiceTest extends AcmePlannerTest {

	/**
	 * This test achieves a 92.6% coverage in the AnonymousShoutListService.
	 * 
	 * Test 1:
	 * In this test we check that the selected task is the same that it shows when we click it in our service.
	 * 
	 * 
	 * Test 2:
	 * As in the anonymous shout listing test, in this one we check there is a list of the tasks, that can be
	 * empty or filled.
	 *
	 * Test 3:
	 * We check that we can't list tasks login as an administrator.
	 */

	/**
	 * 
	 * In this test we check that the selected task is the same that it shows when we click it in our service.
	 * 
	 */

	@Test
	public void anonymousTaskShow() {

		super.navigateHome();
		super.clickAndGo(By.linkText("Anonymous"));

		super.clickOnLink("List unfinished public tasks");

		final String title = super.locateOne(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[2]")).getText();
		final String startDate = super.locateOne(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[3]")).getText();
		final String endDate = super.locateOne(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[4]")).getText();
		final String workLoad = super.locateOne(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[5]")).getText();

		super.clickAndGo((By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[2]")));

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("executionStart", startDate);
		super.checkInputBoxHasValue("executionEnd", endDate);
		super.checkInputBoxHasValue("workloadParsed", workLoad);

	}

}
