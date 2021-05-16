package acme.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

public class AnonymousTest extends AcmePlannerTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/negative-shout.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void negativeSpamCreateShout(final String author, final String text, final String info) {
		this.createShout(author,text,info);
		super.checkErrorsExist();
	}	

	@Test
	public void positiveShoutListing() {
		
		super.navigateHome();
		super.clickAndGo(By.linkText("Anonymous"));
		super.clickAndGo(By.linkText("List shouts"));
		Assertions.assertTrue(super.exists(By.cssSelector(".control.sorting_1")) || super.exists(By.className("dataTables_empty")));
	}

	@Test
	public void negativeShoutListing() {

		super.signIn("administrator", "administrator");
 		super.driver.get("http://localhost:8080/Acme-Planner/anonymous/shout/list");
 		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());


		this.signOut();

	}
	
	protected void createShout(final String author, final String text, final String info) {
		super.navigateHome();
		super.clickAndGo(By.linkText("Anonymous"));
		super.clickAndGo(By.linkText("Create shout"));
		super.fill(By.id("author"), author);
		super.fill(By.id("text"), text);
		super.fill(By.id("info"), info);
		super.clickAndGo(By.className("btn-primary"));
	}
}
