package PageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPage {

    WebDriver driver;
    LoginPage login;
    FormPage form;
    ClickPage click;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/webdriveradvance.html");
        login = PageFactory.initElements(driver, LoginPage.class);
        form = PageFactory.initElements(driver, FormPage.class);
        click = PageFactory.initElements(driver, ClickPage.class);
    }

    @Test
    public void loginTest() {
        login.action("selenium", "webdriver");
        form.action("Software engineer and specializes in cyber", "20", "Israel");
        assertTrue(click.btn_click.isDisplayed());
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
