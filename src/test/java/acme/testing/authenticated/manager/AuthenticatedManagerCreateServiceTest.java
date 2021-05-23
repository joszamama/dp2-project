
package acme.testing.authenticated.manager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedManagerCreateServiceTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/tasks/authenticated/list-finished-public.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void becomeManagerPositive() {
		super.signUp("testusername", "testtest", "testname", "testsurname", "testemail@acme.com");
		super.signIn("testusername", "testtest");
		super.clickOnMenu("Account", "Become a manager");

		super.fillInputBoxIn("company", "test company 1");
		super.fillInputBoxIn("sector", "testing department");
		super.clickOnSubmitButton("Register");

		super.checkSimplePath("/master/welcome");
		super.clickOnMenu("Account", "Manager data");
		super.checkInputBoxHasValue("company", "test company 1");
		super.checkInputBoxHasValue("sector", "testing department");
	}

	// Falta el caso negativo de convertirse en manager
}
