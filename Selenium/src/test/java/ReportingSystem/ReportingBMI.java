package ReportingSystem;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ReportingBMI {

    WebDriver driver;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/bmi");
    }

    @Test(description = "Test BMI Results")
    @Description("BMI Results Test")
    public void testBMI() {
        try {
            update(driver.findElement(By.id("weight")), "63");
            update(driver.findElement(By.id("hight")), "160");
            click(driver.findElement(By.id("calculate_data")));
            verityEquals("50", getAttribute(driver.findElement(By.id("bmi_result"))));
        }
        catch(AssertionError e) {
            saveScreenshot();
            fail("Test Failed: " + e);
        }
        catch(Exception e) {
            saveScreenshot();
            fail("Test Failed: " + e);
        }
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @Step("Update Text Field")
    public void update(WebElement elem, String value) {
        elem.sendKeys(value);
    }

    @Step("Click on Element")
    public void click(WebElement elem) {
        elem.click();
    }

    @Step("Get Text from input Element")
    public String getAttribute(WebElement elem) {
        return elem.getAttribute("value");
    }

    @Step("Verify Results")
    public void verityEquals(String actual, String expected) {
        assertEquals(actual, expected);
    }

    @Attachment(value = "Page Screen-Shot", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
