package MobileGestures;

import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class ExpandableListsMenuPage {

    @FindBy(xpath = "//*[@text='1. Custom Adapter']")
    private AndroidElement btn_custom_adapter;

    @Step
    public void navigeToCustomAdapterPage() {
        btn_custom_adapter.click();
    }
}
