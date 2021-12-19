package MobileMethods;

import Listeners.EventListeners;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.testng.Assert.*;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

@Listeners(EventListeners.class)
public class MobileMethods {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "MobileMethods";
    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();

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
    }


    @Test(priority = 0)
    public void test01() {
        assertTrue(driver.findElementsById("text1").size() == 11, "Unexpected amount of records");
    }

    @Test(priority = 1)
    public void test02() {
        printDimensionsAndCoordinates();
        printAppNameAndDevTime();
        assertTrue(driver.isAppInstalled("com.experitest.ExperiBank"), "This app is not installed");
    }

    @Test(priority = 2)
    public void test03() {
        verifyOrientation();
    }

    @Test(priority = 3)
    public void test04() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        scrollTopMenuAndTakScreenshot(scrFile);
        clickHomeTakeScreenshot(scrFile);
        verifyLockingOpeningDevice();
    }

    @Test(priority = 4)
    public void test05() {
        assertEquals((driver.getPageSource()).split("ListView", -1).length-1, 4, "ListView is not equal to 4");
    }


    @Step
    public void printDimensionsAndCoordinates() {
        System.out.println("Content Height: " + driver.findElement(By.xpath("//*[@text='Content']")).getRect().getHeight());
        System.out.println("Content Width: " + driver.findElement(By.xpath("//*[@text='Content']")).getRect().getWidth());
        System.out.println("Content X Coordinate: " + driver.findElement(By.xpath("//*[@text='Content']")).getRect().getX());
        System.out.println("Content Y Coordinate: " + driver.findElement(By.xpath("//*[@text='Content']")).getRect().getY());
    }

    @Step
    public void printAppNameAndDevTime () {
        System.out.println("Application Name: " + driver.currentActivity());
        System.out.println("Device Time: " + driver.getDeviceTime());
    }

    @Step
    public void verifyOrientation() {
        if(!driver.getOrientation().equals("LANDSCAPE")) {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }

    @Step
    public void scrollTopMenuAndTakScreenshot(File srcFile) throws IOException {
        driver.openNotifications();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        FileUtils.copyFile(srcFile, new File("C:\\Users\\snoim\\OneDrive\\Desktop\\Automation\\QA Automation\\TestAutomation\\Appium\\src\\test\\java\\MobileMethods\\ScreenShots/test04_MobileMethods_notification.png"));
    }

    @Step
    public void clickHomeTakeScreenshot(File scrFile) throws IOException {
        driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\snoim\\OneDrive\\Desktop\\Automation\\QA Automation\\TestAutomation\\Appium\\src\\test\\java\\MobileMethods\\ScreenShots/test04_MobileMethods_home.png"));
    }

    @Step
    public void verifyLockingOpeningDevice() {
        if(!driver.isDeviceLocked()) {
            driver.lockDevice();
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        }
        if(driver.isDeviceLocked()) {
            driver.unlockDevice();
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        }
        assertFalse(driver.isDeviceLocked(), "Device is Locked");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
