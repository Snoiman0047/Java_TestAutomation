package Bonus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import static org.testng.Assert.*;

public class TemperaturePage {

    @FindBy(xpath = "//*[@id='unit_value'][1]")
    private AndroidElement txt_unit;

    @Step
    public void verifyTemperature(AndroidDriver driver, int temperature) {
        txt_unit.sendKeys(String.valueOf(temperature));
        assertTrue(driver.findElement(By.xpath("//*[@text='95']")).isDisplayed());

    }
}
