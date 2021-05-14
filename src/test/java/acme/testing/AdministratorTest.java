package acme.testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

public class AdministratorTest extends AcmePlannerTest {

	// Internal state ---------------------------------------------------------

	// Lifecycle management ---------------------------------------------------

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();
	}

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveSetCustomizationParameters(final String wordList, final String threshold) {
		this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/configuration/show");
		super.fill(By.id("wordList"), wordList);
		super.clickOnSubmitButton("Change parameters");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/configuration/show");
		super.checkInputBoxHasValue("wordList", wordList);
		this.signOut();
	}

	@Test
	public void negativeSetCustomizationParameters() {
		//this.signIn("administrator", "administrator");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/configuration/show");
		super.checkErrorsExist();
		//this.signOut();
	}

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
