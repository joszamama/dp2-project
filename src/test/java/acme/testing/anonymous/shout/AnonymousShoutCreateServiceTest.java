package acme.testing.anonymous.shout;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateServiceTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateShout(final String author, final String text, final String info) {
		this.createShout(author, text, info);
		super.click(By.linkText("Anonymous"));
		super.click(By.linkText("List shouts"));
		// assert super.exists(By.linkText("Account"));
		super.click(By.xpath("//*[@id=\"list\"]/thead/tr/th[2]"));
		super.click(By.xpath("//*[@id=\"list\"]/thead/tr/th[2]"));
		final String momentOfCreation = super.locateOne(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[2]")).getText();
		System.out.println(momentOfCreation);
		final LocalDateTime DateTimeOfCreation = LocalDateTime.parse(momentOfCreation, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")); //, moment);
		// final Boolean momentIsOk = LocalDateTime.parse(momentOfCreation,formatter).getHour() == LocalDateTime.now().getHour();
		// System.out.println(String.format("Hora momento de creación: %s",LocalDateTime.parse(moment,formatter).getHour()));
		System.out.println(String.format("Hora actual: %s", LocalDateTime.now().getHour()));
		final Duration timeDifference = Duration.between(DateTimeOfCreation, LocalDateTime.now());
		System.out.println(timeDifference.toMinutes() + " minutes");
		assert timeDifference.toMinutes() < 3;
		//assert momentIsOk;	
		//			DateTimeFormatter formato;
		//		formato = DateTimeFormatter.ofPattern("dd/MM/yy");
		//		LocalDate fecha = LocalDate.parse(19/07/63, formato);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(1)
	public void negativeSpamCreateShout(final String author, final String text, final String info) {
		this.createShout(author, text, info);
		super.checkErrorsExist();
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
