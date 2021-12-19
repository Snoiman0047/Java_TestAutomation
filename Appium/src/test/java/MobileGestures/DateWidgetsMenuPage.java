package MobileGestures;

import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class DateWidgetsMenuPage {

    @FindBy(xpath = "//*[@text='2. Inline']")
    private AndroidElement btn_inline;

    @Step
    public void navigateToInlinePage() {
        btn_inline.click();
    }
}
