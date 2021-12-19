package BonusExercises.ZapTest;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReservationPage {

    @FindBy(css ="#no-more-tables > tbody > tr")
    private List<WebElement> reservation_flights_list;

    @Step("Get reserved flight")
    public List<WebElement> get_reserved_flight(String flight_code) {
        for(int i = 0; i < reservation_flights_list.size(); i++) {
            if ((reservation_flights_list.get(i).findElements(By.tagName("td")).get(0)).getText().equals(flight_code)) {
                return (reservation_flights_list.get(i).findElements(By.tagName("td")));
            }
        }
        return null;
    }

    @Step("Cancel reserved flight")
    public void cancel_reserved_flight(String flight_code) {
        get_reserved_flight(flight_code).get(6).click();
    }

}
