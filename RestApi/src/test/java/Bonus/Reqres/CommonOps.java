package Bonus.Reqres;

import Bonus.Cat_API.CatWorkFlow;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CommonOps extends Base{

    @BeforeClass
    public void startUp() {
        init_chrome_driver();
        set_url("https://reqres.in/");
        navigate_to_url();
        Uninterruptibles.sleepUninterruptibly(wait_time, TimeUnit.SECONDS);
        init_home_page();
        scroll_and_screenshot(home.get_line_elem());
        init_API();
    }

    @Step("Initialization chrome browser")
    public void init_chrome_driver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://reqres.in/");
    }

    @Step("Update url")
    public void set_url(String url) {
        this.url = url;
    }

    @Step("Navigate to url")
    public static void navigate_to_url() {
        driver.get(url);
    }

    @Step("Initialization nome page")
    public void init_home_page() {
        home = PageFactory.initElements(driver, HomePage.class);
    }

    @Step("Scroll and take screenshot")
    public void scroll_and_screenshot(WebElement element) {
        UIActions.scroll_into_view(element);
        save_screenshot(screenshot_path, "initial_view");
    }

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] save_screenshot(String path, String image_desc) {
        byte[] img_bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        try {
            FileUtils.writeByteArrayToFile(new File(path + image_desc + ".png"), img_bytes);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot");
            e.printStackTrace();
        }
        return img_bytes;
    }

    @Step("Initialization API")
    public void init_API() {
        RestAssured.baseURI = url;
        http_request = RestAssured.given();
    }

    @AfterMethod
    public void afterMethod() {
        CatWorkFlow.verify_response_status_code(200);
    }

    @AfterClass
    public void closeSession() {
        save_screenshot(screenshot_path, "Last_view");
        driver.quit();
    }
}
