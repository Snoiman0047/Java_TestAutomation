package BonusExercises.ZapTest;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class ZapTest extends CommonOps{

    @Test
    @Description("Verification of login to the site")
    public void test01() {
        ZapWorkFlows.verify_login();
    }

    @Test
    @Description("Flight verification check does not exist")
    public void test02() {
        source_country = "New York";
        country_destination = "Los Angeles";
        date_in_month_for_search = 21;
        ZapWorkFlows.search_flight();
        ZapWorkFlows.verify_result_text("No Results are Found");
    }

    @Test
    @Description("Verification of an existing flight")
    public void test03() {
        date_in_month_for_search = 26;
        flight_code = "UA1029";
        ZapWorkFlows.search_flight();
        ZapWorkFlows.verify_flight_is_exists(true);
    }

    @Test
    @Description("Verification check on flight booking")
    public void test04() {
        ZapWorkFlows.book_flight();
        ZapWorkFlows.verify_flight_booked();
    }

    @Test
    @Description("Verification check on future flight")
    public void test05() {
        ZapWorkFlows.navigate_to_reservation();
        ZapWorkFlows.verify_reserved_flight(true);
    }

    @Test
    @Description("Verification check for cancellation of future flight")
    public void test06() {
        ZapWorkFlows.cancel_reserved_flight();
        ZapWorkFlows.verify_reserved_flight_was_canceled(true);
    }

    @Test
    @Description("Verification of availability of available flights")
    public void test07() {
        ZapWorkFlows.navigate_to_ordering_system();
        source_country = "Atlanta";
        country_destination = "New York";
        date_in_month_for_search = 26;
        ZapWorkFlows.search_flight();
        ZapWorkFlows.verify_search_flight_amount(2);
    }
}
