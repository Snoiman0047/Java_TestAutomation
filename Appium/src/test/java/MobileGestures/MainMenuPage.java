package MobileGestures;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class MainMenuPage {

    @FindBy(xpath = "//*[@text='Views']")
    private AndroidElement btn_views;

    @Step
    public void navigateToViewsPage() {
        btn_views.click();
    }
}
