package DataDrivenTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class DDT {

    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://wikipedia.org/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @DataProvider(name = "wikipedia-data-provider")
    public Object[][] getDataObject() {
        return new Object[][] {
                {"Israel", "Israel"},
                {"Automation", "Automation"},
                {"BlahBlah", "Search results"}
        };
    }

    @Test(dataProvider = "wikipedia-data-provider")
    public void testIt(String key, String value) {
        driver.findElement(By.id("searchInput")).sendKeys(key);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        assertEquals(driver.findElement(By.id("firstHeading")).getText(), value);
        driver.get("http://wikipedia.org/");
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
