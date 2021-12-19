package BonusExercises.ZapTest;

import org.openqa.selenium.WebDriver;

public class Base {

    protected static WebDriver driver;

    protected final static String user_name = "test", password = "demo";
    protected static String url, country_destination, source_country, flight_code;
    protected static int date_in_month_for_search;

    protected static LoginPage login;
    protected static SearchFlightPage search_flight;
    protected static ReservationPage reservation_flights;
}
