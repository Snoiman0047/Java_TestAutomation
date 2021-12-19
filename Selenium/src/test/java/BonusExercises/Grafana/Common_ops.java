package BonusExercises.Grafana;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

@Listeners(Event_listeners.class)
public class Common_ops extends Base {

    @BeforeClass
    public void startUp() {
        set_url("http://localhost:3000/login");
        init_chrome_driver();
        navigate_to_url();
        sleep(2);
        init_API();
        init_login_page();
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


    @Step("Initialization API")
    public void init_API() {
        RestAssured.baseURI = http_request_url;
        http_request = RestAssured.given();
    }

    @Step("Initialization login page")
    public void init_login_page() {
        _login = PageFactory.initElements(driver, Login_page.class);
    }

    @Step("Initialization password page")
    public static void init_password_page() {
        _password = PageFactory.initElements(driver, Password_page.class);
        sleep(1);
    }

    @Step("Initialization private area page")
    public static void init_private_area_page() {
        _private_area = PageFactory.initElements(driver, Personal_area_page.class);
    }

    @Step("Initialization configuration page")
    public static void init_configuration_page() {
        _configuration = PageFactory.initElements(driver, Configuration_page.class);
    }

    @Step("Sleep action")
    public static void sleep(int wait_time) {
        Uninterruptibles.sleepUninterruptibly(wait_time, TimeUnit.SECONDS);
    }


    @AfterClass
    public void closeSession() {
        _private_area.changeToDarkBackgroundColor();
        driver.quit();
    }
}
