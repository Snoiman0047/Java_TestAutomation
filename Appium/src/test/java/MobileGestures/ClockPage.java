package MobileGestures;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.ElementOption;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;

public class ClockPage {

    @FindBy(xpath = "//*contentDescription='12'")
    private AndroidElement hour_start_location;

    @FindBy(xpath = "//*contentDescription='15'")
    private AndroidElement minutes_start_location;

    @Step
    public void changeHour(AndroidDriver driver) {
        TouchAction touch = new TouchAction(driver);
        touch.press(new ElementOption().withElement(hour_start_location)).moveTo(new ElementOption().withCoordinates(282, 894)).release().perform();
        touch.press(new ElementOption().withElement(minutes_start_location)).moveTo(new ElementOption().withCoordinates(251, 776)).release().perform();
    }

}
