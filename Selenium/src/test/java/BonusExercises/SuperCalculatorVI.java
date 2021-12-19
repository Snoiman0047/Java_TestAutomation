package BonusExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class SuperCalculatorVI {

    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://juliemr.github.io/protractor-demo/");
    }

    @Test
    public void testIt() {
        Support.printTextWebElementList(Support.dupCalculation(driver, 3));
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
