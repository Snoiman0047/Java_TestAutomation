package ErrorHandling;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@Listeners(AutomationListeners.class)
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
    public void findElementAfterSleep_01() {
        driver.findElement(By.id("btn")).click();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        try {
            driver.findElement(By.id("checkbox"));
        }
        catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Element not founded");
        }
    }

    @Test
    public void findElementAfterSleep_02() {
        driver.findElement(By.id("btn")).click();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        assertTrue(driver.findElements(By.id("checkbox")).isEmpty());
    }

    @Test
    public void findElementAfterSleep_03() {
        driver.findElement(By.id("btn")).click();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        driver.findElement(By.id("checkbox"));
    }

    @AfterMethod
    public void afterMethod() {
        driver.navigate().refresh();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
