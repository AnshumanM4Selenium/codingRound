import com.codoing.businessUtility.ClearTripHomePage;
import com.codoing.utility.Config;
import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightBookingTest extends Config {
	
	ClearTripHomePage chp = new ClearTripHomePage();
	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		driver.get(Config.getBaseURL());

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LocalDate futureDate = LocalDate.of(2019, Month.AUGUST, 1);
		String depatureDate = futureDate.format(DateTimeFormatter.ISO_DATE);
		chp.searchFlightForOneWay("Bangalore", "New Delhi", depatureDate);
		

	}

	
	
	
}
