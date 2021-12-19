package MobileGestures;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class ViewsMenuPage {

    @FindBy(xpath = "//*[@text='Date Widgets']")
    private AndroidElement btn_data_widgets;

    @FindBy(xpath = "//*[@text='Expandable Lists']")
    private AndroidElement btn_expandable_lists;

    @Step
    public void navigateToDataWidgetsPage() {
        btn_data_widgets.click();
    }

    @Step
    public void navigateToExpandableListsPage() {
        btn_expandable_lists.click();
    }
}
