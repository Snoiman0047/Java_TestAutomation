package BonusExercises.ZapTest;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchFlightPage {

    @FindBy(css = "#to option")
    private List<WebElement> country_destination_options;

    @FindBy(css = "#from option")
    private List<WebElement> source_country_options;

    @FindBy(id = "dateTo")
    private WebElement dp_data_to_search_for;

    @FindBy(css = "div.col-md-2.form-group > button")
    public WebElement btn_search;

    @FindBy(tagName = "h4")
    private WebElement txt_search_result;

    @FindBy(css = "p > span")
    private WebElement page_title;

    @FindBy(css = "div:nth-child(3) > ul > li:nth-child(1) > a")
    private WebElement btn_reservation;

    @FindBy(css = "div.navbar-header.hidden-xs > a")
    private WebElement link_ordering_system;


    @Step("Get page title")
    public String get_page_title() {
        return page_title.getText();
    }

    @Step("Set destination country")
    public void set_destination_country(String country) {
        for (WebElement elem : country_destination_options) {
            if (elem.getText().equals(country)) {
                elem.click();
                break;
            }
        }
    }

    @Step("Set source country")
    public void set_source_country(String country) {
        for (WebElement elem : source_country_options) {
            if (elem.getText().equals(country)) {
                elem.click();
                break;
            }
        }
    }

    @Step("Set date for flight to search")
    public void set_date_to_search_flight(WebDriver driver, int data_in_month) {
        dp_data_to_search_for.click();
        List<WebElement> dates = driver.findElements(By.cssSelector("div.days > div.day"));
        for(WebElement elem: dates) {
            if (elem.getText().equals(String.valueOf(data_in_month))) {
                elem.click();
                break;
            }
        }
    }

    @Step("Click on search button")
    public void search_click() {
        btn_search.click();
    }

    @Step("Click on reservation button")
    public void reservation_click() {
        btn_reservation.click();
    }

    @Step("Get alert text")
    public String get_alert_text(WebDriver driver) {
        return driver.findElement(By.cssSelector("alert > div")).getText();
    }

    @Step("Get search result title")
    public String get_search_result() {
        return txt_search_result.getText();
    }

    @Step("Get flight by flight code")
    public static List<WebElement> get_flight(WebDriver driver, String flight_code) {
        List<WebElement> flights_result = get_all_flights(driver);
        for(int i = 0; i < flights_result.size(); i++) {
            if ((flights_result.get(i).findElements(By.tagName("td")).get(0)).getText().equals(flight_code)) {
                return flights_result.get(i).findElements(By.tagName("td"));
            }
        }
        return null;
    }

    @Step("Get all founded flights")
    public static List<WebElement> get_all_flights(WebDriver driver) {
        return driver.findElements(By.cssSelector("tbody > tr"));
    }

    @Step("Book flight")
    public static void book_flight(WebDriver driver, String flight_code) {
        (get_flight(driver, flight_code)).get(4).findElement(By.cssSelector("span > button")).click();
    }

    @Step("Navigate to ordering system")
    public void navigate_to_ordering_system() {
        link_ordering_system.click();
    }

}
