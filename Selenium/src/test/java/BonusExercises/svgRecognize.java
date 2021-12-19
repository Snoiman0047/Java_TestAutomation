package BonusExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class svgRecognize {

    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/my-list.html");
    }

    @Test
    public void test01_recognize_svg_tag() {
        driver.findElement(By.cssSelector("div:nth-child(4) > div > div > label > svg > path"));
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}

