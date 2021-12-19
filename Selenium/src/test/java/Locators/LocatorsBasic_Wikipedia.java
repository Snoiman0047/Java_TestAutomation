package Locators;

import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.List;

public class LocatorsBasic_Wikipedia {
    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://wikipedia.org/");
    }

    @Test
    public void test() {
        WebElement mainLogo = driver.findElement(By.className("central-featured-logo"));
        WebElement searchField = driver.findElement(By.id("searchInput"));
        WebElement selectLanguage = driver.findElement(By.id("searchLanguage"));
        WebElement footerSideBar = driver.findElement(By.className("footer-sidebar-content"));

        List<WebElement> wikiElements = new ArrayList<>();
        wikiElements.add(mainLogo);
        wikiElements.add(searchField);
        wikiElements.add(selectLanguage);
        wikiElements.add(footerSideBar);

        for(int i = wikiElements.size() - 1; i >= 0; i--)
            System.out.println(wikiElements.get(i));
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
