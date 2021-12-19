package Bonus;

import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class MainMenuPage {

    @FindBy(xpath = "//*[@text='LIVING']")
    private AndroidElement btn_living;

    @Step
    public void navigateToLivingPage() {
        btn_living.click();
    }
}
