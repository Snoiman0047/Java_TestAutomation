package  ExternalFiles;

import static org.testng.Assert.*;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExternalBMIFile {
    WebDriver driver;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfData.getData("Url"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void checkBMI() {
        driver.findElement(By.id("weight")).sendKeys(ConfData.getData("Weight"));
        driver.findElement(By.name("height")).sendKeys(ConfData.getData("Height"));
        driver.findElement(By.id("calculate_data")).click();
        String ActualResult = driver.findElement(By.id("bmi_result")).getAttribute("value");
        assertEquals(ActualResult, ConfData.getData("ExpectedResultBMI"));
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}

