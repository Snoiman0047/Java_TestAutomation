package BonusExercises.Grafana;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;

@Listeners(Event_listeners.class)
public class Password_page {

    @FindBy(css = "div.css-66nrdr-vertical-group > div:nth-child(2) > button")
    private WebElement btn_skip;

    @Step("Click on skip button")
    public void skip() {
        btn_skip.click();
    }
}
