package ExecuteAppium;

import Listeners.EventListeners;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

@Listeners(EventListeners.class)
public class EribankTest {

    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Eribank_recordTest";
    protected AndroidDriver<AndroidElement> driver = null;
    LoginPage login;
    PersonalAreaActionsPage personalAreaActions;
    PaymentPage payment;
    double balance, price;
    String userName, password;

    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "R58M944ZE2A");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);

        login = PageFactory.initElements(driver, LoginPage.class);
        personalAreaActions = PageFactory.initElements(driver, PersonalAreaActionsPage.class);
        payment = PageFactory.initElements(driver, PaymentPage.class);
        userName = password = "company";
    }

    @Test(priority = 0)
    public void test01_login () {
        login.login(userName, password);
        login.verifyLogin(driver);
    }

    @Test(priority = 1, dependsOnMethods = "test01_login")
    public void test02_entrancePersonalAreaActions () {
        balance = personalAreaActions.getBalance();
        login.verifyLogin(driver);
        personalAreaActions.makePayment();
        personalAreaActions.verifyMakePaymentPage(driver);
    }

    @Test(priority = 2, dependsOnMethods = "test02_entrancePersonalAreaActions")
    public void test03_makePayment() {
        price = 100;
        payment.makeAndSendPayment("0000000", "Unknown", price, "Israel");
        driver.findElement(By.xpath("xpath=//*[@text='Yes']")).click();
        payment.verifyPaymentDone(driver);
    }

    @Test(priority = 3, dependsOnMethods = "test03_makePayment")
    public void test04_verifyPayment() {
        personalAreaActions.verifyBalance(balance, price);
    }

    @Test(priority = 4, dependsOnMethods = "test04_verifyPayment")
    public void test05_logOut() {
        personalAreaActions.logOut();
        personalAreaActions.verifyLogOut(driver);
    }

    @Test(priority = 5, dependsOnMethods = "test05_logOut")
    public void test06_loginAgain() throws InterruptedException {
        test01_login();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

