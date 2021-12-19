package BonusExercises.ZapTest;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class CommonOps extends Base{

    @BeforeClass
    public void startUp() {
        set_url("http://demo.zaptest.com/login");
        init_chrome_driver();
        navigate_to_url();
        sleep(8);
        init_login_page();
        ZapWorkFlows.login(user_name, password);
    }

    @Step("Initialization chrome browser")
    public void init_chrome_driver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Step("Update url")
    public void set_url(String url) {
        this.url = url;
    }

    @Step("Navigate to url")
    public static void navigate_to_url() {
        driver.get(url);
    }

    @Step("Initialization login page")
    public void init_login_page() {
        login = PageFactory.initElements(driver, LoginPage.class);
    }

    @Step("Initialization search flight page")
    public static void init_search_flight_page() {
        search_flight = PageFactory.initElements(driver, SearchFlightPage.class);
        sleep(2);
    }

    @Step("Initialization reservation flight page")
    public static void init_reservation_flight_page() {
        reservation_flights = PageFactory.initElements(driver, ReservationPage.class);
        sleep(2);
    }

    @Step
    public static void sleep(int wait_time) {
        Uninterruptibles.sleepUninterruptibly(wait_time, TimeUnit.SECONDS);
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
