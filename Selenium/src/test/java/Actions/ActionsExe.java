package Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class ActionsExe {
    WebDriver driver;
    org.openqa.selenium.interactions.Actions action;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_actions.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        action = new org.openqa.selenium.interactions.Actions(driver);
    }

    @Test
    public void test01_DragDrop (){
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        action.dragAndDrop(drag, drop).build().perform();
        assertEquals(drop.getText(), "Element was dropped!");
    }

    @Test
    public void test02_MultiSelect() {
        List<WebElement> elems = driver.findElements(By.xpath("//*[@id='select_items']/li"));
        action.clickAndHold(elems.get(1)).clickAndHold(elems.get(2)).click().build().perform();
    }

    @Test
    public void test03_DoubleClick() {
        action.doubleClick(driver.findElement(By.id("dbl_click"))).build().perform();
        assertEquals(driver.findElement(By.id("demo")).getText(), "Hello World");
    }

    @Test
    public void test04_OnMouseHover() {
        WebElement element = driver.findElement(By.id("mouse_hover"));
        action.moveToElement(element).click().build().perform();
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
        assertTrue(driver.findElement(By.cssSelector("span[style='background-color: rgb(255, 255, 0);']")).isDisplayed());
    }

    @Test
    public void test05_Scroll() {
        WebElement elem = driver.findElement(By.id("scrolled_element"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
        assertEquals(driver.findElement(By.id("scrolled_element")).getText(), "This Element is Shown When Scrolled");
    }

    @AfterMethod
    public void afterMethod() {
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
