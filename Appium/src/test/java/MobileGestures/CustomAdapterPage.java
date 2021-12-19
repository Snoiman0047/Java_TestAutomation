package MobileGestures;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static org.testng.Assert.*;
import org.openqa.selenium.support.FindBy;

public class CustomAdapterPage {

//    @FindBy(xpath = "//*[@text='People Names']")
//    private AndroidElement btn_people_names;

    @Step
    public void press_people_name(AndroidDriver driver) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press((PointOption) driver.findElement(By.xpath("//*[@text='People Names']"))).release().perform();
        assertEquals(driver.findElement(By.xpath("//*[@text='Sample action']")).getText(), "Sample action");
    }

    @Step
    public void tap_people_name(AndroidDriver driver) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap((TapOptions) driver.findElement(By.xpath("//*[@text='People Names']")));
        assertEquals(driver.findElement(By.xpath("//*[@text='Sample action']")).getText(),"Sample action");

    }

    @Step
    public void performTouchAction_people_name(AndroidDriver driver) {
        TouchAction touchAction = new TouchAction(driver);
        driver.performTouchAction(touchAction.longPress((LongPressOptions) driver.findElement(By.xpath("//*[@text='People Names']"))));
        assertEquals(driver.findElement(By.xpath("//*[@text='Sample action']")).getText(),"Sample action");
    }

    @Step
    public void verify_people_name(AndroidDriver driver) {
        driver.findElement(By.xpath("//*[@text='Sample action']")).click();
    }

}
