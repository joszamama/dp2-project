
package acme.testing;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.framework.helpers.StringHelper;

public abstract class AcmePlannerTest extends AcmeTest {

	////////////////////////////////////////////////////////////////////////////////
	// Lifecycle management

	@Override
	@BeforeAll
	public void beforeAll() {
		super.setAutoPausing(true);
		super.setHeadless(true);
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8080", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");

		this.navigateHome();
		this.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Populate DB (samples)");
		this.checkAlertExists(true);
		this.signOut();
	}

	////////////////////////////////////////////////////////////////////////////////
	// Business methods

	protected void signIn(final String username, final String password) {
		assert !StringHelper.isBlank(username);
		assert !StringHelper.isBlank(password);

		super.navigateHome();
		super.clickOnMenu("Sign in", null);
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("remember", "true");
		super.clickOnSubmitButton("Sign in");
		super.checkSimplePath("/master/welcome");
		super.checkLinkExists("Account");
	}

	protected void signOut() {
		super.navigateHome();
		super.clickOnMenu("Sign out", null);
		super.checkSimplePath("/master/welcome");
	}

	protected void signUp(final String username, final String password, final String name, final String surname, final String email) {
		assert !StringHelper.isBlank(username);
		assert !StringHelper.isBlank(password);
		assert !StringHelper.isBlank(name);
		assert !StringHelper.isBlank(surname);
		assert !StringHelper.isBlank(email);

		super.navigateHome();
		super.clickOnMenu("Sign up", null);
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", password);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.email", email);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("accept", "true");
		super.clickOnSubmitButton("Sign up");
		super.checkSimplePath("/master/welcome");
	}

	////////////////////////////////////////////////////////////////////////////////
	// Check not exist methods

	/*
	 * The method to find elements asserts that results exist, so
	 * it throws an exception if no elements are found
	 * 
	 * These methods side-step that method to avoid exceptions
	 */

	@Override
	protected void checkNotErrorsExist() {
		final List<WebElement> errors = this.driver.findElements(By.className("text-danger"));
		assert errors.isEmpty() : "No errors were expected in current form";
	}

	protected void checkNotElementsExist(final By locator) {
		final List<WebElement> elements = this.driver.findElements(locator);
		assert elements.isEmpty();
	}

	////////////////////////////////////////////////////////////////////////////////
	// Other methods

	public void click(final By locator) {
		assert locator != null;
		WebElement element;
		element = this.locateOne(locator);
		element.click();
		this.shortSleep();
	}

}
