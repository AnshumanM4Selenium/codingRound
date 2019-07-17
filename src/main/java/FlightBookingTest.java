import com.codoing.businessUtility.ClearTripHomePage;
import com.codoing.utility.Config;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class FlightBookingTest extends Config {
	
	ClearTripHomePage chp = new ClearTripHomePage();
	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		LocalDate futureDate = LocalDate.of(2019, Month.AUGUST, 1);
		String depatureDate = futureDate.format(DateTimeFormatter.ISO_DATE);
		chp.searchFlightForOneWay("Bangalore", "New Delhi", depatureDate);
	}	
	
}
