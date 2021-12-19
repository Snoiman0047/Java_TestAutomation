package WebDriverMethods;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WDM_02 {

    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void test() {
        driver.get("http://google.com");
        driver.get("http://bing.com");
        driver.navigate().back();
        System.out.println("Title is: " + driver.getTitle());

        if(driver.getPageSource().contains("bubble"))
            System.out.println("Exists");
        else
            System.out.println("Not Exists");
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }

}
