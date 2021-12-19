package AutomatingGraphicElements;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import java.util.concurrent.TimeUnit;

public class AGE {
    WebDriver driver;
    Screen screen;
    final String path = "C:/Users/snoim/OneDrive/Desktop/Automation/QA Automation/TestAutomation/Selenium/src/test/java/AutomatingGraphicElements/SikuliImageRepository/";

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://maps.google.com");
        screen = new Screen();
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
    }

    @Test
    public void googleMaps() throws FindFailed {
        screen.click(path + "zoomIn.png");
        screen.click(path + "zoomOut.png");
        screen.type(path + "searchField.png", "Ness Israel");
        screen.click(path + "searchButton.png", 3);
        screen.click(path + "ness.png");
        screen.click(path + "route.png");
        screen.type(path + "startPosition.png", "Elad, Israel");
        screen.click(path + "searchRoute.png");
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
