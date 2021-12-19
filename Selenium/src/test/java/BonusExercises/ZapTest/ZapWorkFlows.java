package BonusExercises.ZapTest;

import io.qameta.allure.Step;

public class ZapWorkFlows extends CommonOps{

    @Step("Login by user name and password")
    public static void login(String user_name, String password) {
        login.send_user_name(user_name);
        login.send_password(password);
        login.login_action();
        init_search_flight_page();
    }

    @Step("verify login")
    public static void verify_login() {
        Validation.verifyValues(search_flight.get_page_title(), "Welcome Bob Dresslay,");
    }

    @Step("Search flight by locations and date")
    public static void search_flight() {
        sleep(2);
        search_flight.set_source_country(source_country);
        search_flight.set_destination_country(country_destination);
        search_flight.set_date_to_search_flight(driver, date_in_month_for_search);
        search_flight.search_click();
        sleep(8);
    }

    @Step("Book flight")
    public static void book_flight() {
        search_flight.book_flight(driver, flight_code);
    }

    @Step("Verify search result title")
    public static void verify_result_text(String expected_text_result) {
        Validation.verifyValues(search_flight.get_search_result(), expected_text_result.toUpperCase());
    }

    @Step("Verify flight was booked")
    public static void verify_flight_booked() {
        sleep(2);
        Validation.verifyValues(search_flight.get_alert_text(driver), "The flight \"" + flight_code + "\"\" is booked.");
    }

    @Step("Verify specific flight is exists")
    public static void verify_flight_is_exists(boolean is_exists) {
        Validation.verifyValues(is_exists, search_flight.get_flight(driver, flight_code) != null);
    }

    @Step("Navigate to reservation page")
    public static void navigate_to_reservation() {
        search_flight.reservation_click();
        init_reservation_flight_page();
    }

    @Step("Verify flight was reserved")
    public static void verify_reserved_flight(boolean is_reserved) {
        Validation.verifyValues(is_reserved, reservation_flights.get_reserved_flight(flight_code) != null);
    }

    @Step("Cancel reserved flight")
    public static void cancel_reserved_flight() {
        reservation_flights.cancel_reserved_flight(flight_code);
        sleep(3);
    }

    @Step("Verify reserved flight was canceled")
    public static void verify_reserved_flight_was_canceled(boolean is_canceled) {
        Validation.verifyValues(reservation_flights.get_reserved_flight(flight_code) == null, is_canceled);
    }

    @Step("Navigate to ordering system")
    public static void navigate_to_ordering_system() {
        search_flight.navigate_to_ordering_system();
    }

    @Step("Verify result flight amount after searching")
    public static void verify_search_flight_amount(int amount) {
        Validation.verifyValues(search_flight.get_all_flights(driver).size(), amount);
    }
}
