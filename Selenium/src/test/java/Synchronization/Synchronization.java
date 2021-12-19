package Synchronization;

import static org.testng.Assert.*;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class Synchronization {

    WebDriver driver;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");
    }

    @Test
    public void explicitWait() {
        driver.findElement(By.id("rendered")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish2")));
        String output = driver.findElement(By.id("finish2")).getText();
        assertEquals(output, "My Rendered Element After Fact!");
    }

    @Test
    public void sleep() {
        driver.findElement(By.id("hidden")).click();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("div[id='loading1'][style='display: none;']"));
    }

    @Test
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("btn")).click();
        assertTrue(driver.findElement(By.id("message")).isDisplayed());
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
