
package acme.testing.anonymous.shout;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateServiceTest extends AcmePlannerTest {

	/**
	 * These tests achieve a 96.1% coverage in the AnonymousShoutCreateService.
	 * 
	 * Test 1:
	 * Access the create shout function, create a new positive case of a shout and check that the moment of the
	 * creation of the shout is less than 3 minutes before now. That means it is the same shout that we have
	 * created.
	 * 
	 * Test 2:
	 * In this case, we create a shout with some "spam" words in the text and we check that we aren´t 
	 * allowed to create it because the spam error appeared. We also check bad url's, authors with less than 5 letters.
	 *
	 */
	
	
	/**
	 * Access the create shout function, create a new positive case of a shout and check that the moment of the
	 * creation of the shout is less than 3 minutes before now. That means it is the same shout that we have
	 * created.
	 * @param author
	 * @param text
	 * @param info
	 * 
	 * 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveCreateShout(final String author, final String text, final String info) {

		super.driver.get("http://localhost:8080/Acme-Planner/master/welcome?language=en&debug=true");
		final WebDriverWait wait = new WebDriverWait(super.driver, 10);
		this.createShout(author, text, info);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Anonymous")));
		super.click(By.linkText("Anonymous"));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("List shouts")));
		super.click(By.linkText("List shouts"));
		super.click(By.xpath("//*[@id=\"list\"]/thead/tr/th[2]"));
		super.click(By.xpath("//*[@id=\"list\"]/thead/tr/th[2]"));
		final String momentOfCreation = super.locateOne(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[2]")).getText();
		System.out.println(momentOfCreation);
		final LocalDateTime DateTimeOfCreation = LocalDateTime.parse(momentOfCreation, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")); //, moment);
		System.out.println(String.format("Hora actual: %s", LocalDateTime.now().getHour()));
		final Duration timeDifference = Duration.between(DateTimeOfCreation, LocalDateTime.now());
		System.out.println(timeDifference.toMinutes() + " minutes");
		assert timeDifference.toMinutes() < 3;
	}

	/**
	 * In this case, we create a shout with some "spam" words in the text and we check that we aren´t 
	 * allowed to create it because the spam error appeared. 
	 * We also check bad url's, authors with less than 5 letters.
	 * @param author
	 * @param text
	 * @param info
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeCreateShout(final String author, final String text, final String info) {
		super.driver.get("http://localhost:8080/Acme-Planner/master/welcome?language=en&debug=true");
		this.createShout(author, text, info);
		super.checkErrorsExist();
	}

	/**
	 * This is an auxiliar function to create the shout.
	 * @param author
	 * @param text
	 * @param info
	 */
	protected void createShout(final String author, final String text, final String info) {
		super.driver.get("http://localhost:8080/Acme-Planner/master/welcome?language=en&debug=true");
		super.click(By.linkText("Anonymous"));
		super.click(By.linkText("Create shout"));
		super.fill(By.id("author"), author);
		super.fill(By.id("text"), text);
		super.fill(By.id("info"), info);
		super.click(By.className("btn-primary"));
	}

}
