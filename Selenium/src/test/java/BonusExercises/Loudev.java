package BonusExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Loudev {

    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://loudev.com/");
    }

    @Test
    public void testIt() {
        Support.verifyElements(driver);
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }

}
