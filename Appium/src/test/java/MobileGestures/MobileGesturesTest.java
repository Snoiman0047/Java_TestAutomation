package MobileGestures;

import ExecuteAppium.LoginPage;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;

import static org.testng.Assert.*;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class MobileGesturesTest {
    private String expectedHours;
    private String expectedMinutes;
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "MobileGesturesTest";
    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();

    ClockPage clock;
    MainMenuPage main_menu;
    ViewsMenuPage view_menu;
    DateWidgetsMenuPage data_widgets_menu;
    ExpandableListsMenuPage expandable_list_menu;
    CustomAdapterPage custom_adapter;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "R58M944ZE2A");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);

        expectedHours = "9";
        expectedMinutes = "45";
        main_menu = PageFactory.initElements(driver, MainMenuPage.class);
        view_menu = PageFactory.initElements(driver, ViewsMenuPage.class);
        data_widgets_menu = PageFactory.initElements(driver, DateWidgetsMenuPage.class);
        clock = PageFactory.initElements(driver, ClockPage.class);
        expandable_list_menu =  PageFactory.initElements(driver, ExpandableListsMenuPage.class);
        custom_adapter = PageFactory.initElements(driver, CustomAdapterPage.class);
    }

    @Test(priority = 0)
    public void test01() {
        navigateToClockPage();
        clock.changeHour(driver);
        verifyClockTime();
    }

    @Test(priority = 1)
    public void test02() {
        navigateToHome();
        navigateToCustomAdapter();
        custom_adapter.press_people_name(driver);
        custom_adapter.verify_people_name(driver);
    }

    @Test(priority = 2)
    public void test03() {
        custom_adapter.tap_people_name(driver);
        custom_adapter.verify_people_name(driver);
    }

    @Test(priority = 3)
    public void test04() {
        custom_adapter.performTouchAction_people_name(driver);
        custom_adapter.verify_people_name(driver);
    }


    @Step
    public void navigateToClockPage() {
        main_menu.navigateToViewsPage();
        view_menu.navigateToDataWidgetsPage();
        data_widgets_menu.navigateToInlinePage();
    }

    @Step
    public void verifyClockTime() {
        String current_time = driver.findElement(By.id("hours")).getText() + driver.findElement(By.id("separator")).getText() + driver.findElement(By.id("minutes")).getText();
        assertEquals(current_time, "09:45");
    }

    @Step
    public void navigateToHome() {
        driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
    }

    @Step
    public void navigateToCustomAdapter() {
        main_menu.navigateToViewsPage();
        view_menu.navigateToExpandableListsPage();
        expandable_list_menu.navigeToCustomAdapterPage();
    }

    @AfterMethod
    public void afterMethod() {
        driver.resetApp();
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
