package Locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LocatorsBasic {

    WebDriver driver;

    @BeforeClass
    public void startSession() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://selenium.dev/");
    }

    @AfterClass
    public void endSession() {
        driver.quit();
    }

    @Test
    public void test(){

        System.out.println(driver.findElement(By.className("navbar-brand")));
        System.out.println(driver.findElement(By.className("navbar-logo")));
        System.out.println(driver.findElement(By.id("selenium_logo")));
        System.out.println(driver.findElement(By.tagName("svg")));
        System.out.println(driver.findElements(By.tagName("svg")).get(0));

        List<WebElement> links = driver.findElements(By.tagName("a"));
        List<WebElement> Seleniumlinks = driver.findElements(By.partialLinkText("Selenium"));
        List<WebElement> seleniumlinks = (driver.findElements(By.partialLinkText("selenium")));
        System.out.println("Number of total links in page: " + links.size());
        System.out.println("Number of links in page with word: Selenium is: " + Seleniumlinks.size());
        System.out.println("Number of links in page with word: selenium is: " + seleniumlinks.size());
    }
}
