package Bonus;

import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class SecondaryMenuPage {

    @FindBy(id = "tab1_layout1")
    private AndroidElement btn_temperature;

    @Step
    public void navigateToTemperaturePage() {
        btn_temperature.click();
    }
}
