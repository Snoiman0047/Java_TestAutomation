package LocatorsAdvanced;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocatorsAdvanced {

    WebDriver driver;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_locators.html");
    }

    @Test
    public void Test() {
        WebElement locatorByID = driver.findElement(By.id("locator_id"));
        WebElement locatorByName = driver.findElement(By.name("locator_name"));
        WebElement locatorByClassName = driver.findElement(By.className("locator_class"));
        WebElement locatorByTag = driver.findElement(By.tagName("p"));
        WebElement locatorByLinkText = driver.findElement(By.linkText("myLocator(5)"));
        WebElement locatorByPartialLinkText = driver.findElement(By.partialLinkText("Find my locator"));
        WebElement locatorByCssSelector = driver.findElement(By.cssSelector("input[myname='selenium']"));
        WebElement locatorByXPath = driver.findElement(By.xpath("//*[@id='contact_info_left']/button"));

        System.out.println(locatorByID);
        System.out.println(locatorByName);
        System.out.println(locatorByClassName);
        System.out.println(locatorByTag);
        System.out.println(locatorByLinkText);
        System.out.println(locatorByPartialLinkText);
        System.out.println(locatorByCssSelector);
        System.out.println(locatorByXPath);
    }

    @Test
    public void Bonus() {
        WebElement locatorByID = driver.findElement(By.id("locator_id"));
        WebElement locatorByName = driver.findElement(By.name("locator_name"));
        WebElement locatorByClassName = driver.findElement(By.className("locator_class"));
        WebElement locatorByTag = driver.findElement(By.tagName("p"));
        WebElement locatorByLinkText = driver.findElement(By.linkText("myLocator(5)"));
        WebElement locatorByPartialLinkText = driver.findElement(By.partialLinkText("Find my locator"));
        WebElement locatorByCssSelector = driver.findElement(By.cssSelector("input[myname='selenium']"));
        WebElement locatorByXPath = driver.findElement(By.xpath("//*[@id='contact_info_left']/button"));

        System.out.println(locatorByID.getText());
        System.out.println(locatorByName.getText());
        System.out.println(locatorByClassName.getText());
        System.out.println(locatorByTag.getText());
        System.out.println(locatorByLinkText.getText());
        System.out.println(locatorByPartialLinkText.getText());
        System.out.println(locatorByCssSelector.getAttribute("value"));
        System.out.println(locatorByXPath.getText());
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
