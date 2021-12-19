package BonusExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SuperCalculatorVII {

    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.random.org/");
    }

    @Test
    public void testIt() {
        int numRandom = Support.random(driver);
        Support.navigateToNewTab(driver, "http://juliemr.github.io/protractor-demo/");
        Support.print(numRandom, Support.sumOfNumberMultiBySmallerDigits(driver, numRandom));
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
