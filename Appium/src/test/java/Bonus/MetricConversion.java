package Bonus;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class MetricConversion {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();

    MainMenuPage main_menu;
    SecondaryMenuPage secondary_menu;
    TemperaturePage temperature;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "R58M944ZE2A");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "kr.sira.unit");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".Intro");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);

        main_menu = PageFactory.initElements(driver, MainMenuPage.class);
        secondary_menu = PageFactory.initElements(driver, SecondaryMenuPage.class);
        temperature = PageFactory.initElements(driver, TemperaturePage.class);
        new WebDriverWait(driver, 30);
    }

    @Test
    public void testUntitled() {
        main_menu.navigateToLivingPage();
        secondary_menu.navigateToTemperaturePage();
        temperature.verifyTemperature(driver,35);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
