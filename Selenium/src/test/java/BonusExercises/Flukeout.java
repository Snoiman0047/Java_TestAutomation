package BonusExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Flukeout {
    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://flukeout.github.io/");
    }

    @Test
    public void testIt_cssSelector() {
        System.out.println(driver.findElements(By.cssSelector("a")).size());;
    }

    @Test
    public void testIt_xpath() {
        driver.get("https://topswagcode.com/xpath/");
        System.out.println(driver.findElements(By.xpath("//a")).size());;
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
