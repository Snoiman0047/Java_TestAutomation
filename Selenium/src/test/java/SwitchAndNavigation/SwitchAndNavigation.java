package SwitchAndNavigation;

import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.ArrayList;

public class SwitchAndNavigation {

    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_switch_navigation.html");
    }

    @Test
    public void showAlert() {
        driver.findElement(By.id("btnAlert")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text: " + alert.getText());
        alert.accept();
        assertEquals(driver.findElement(By.id("output")).getText(), "Alert is gone.");
    }

    @Test
    public void showPrompt() {
        driver.findElement(By.id("btnPrompt")).click();
        Alert prompt = driver.switchTo().alert();
        String value = "Selenium in java";
        prompt.sendKeys(value);
        System.out.println("Prompt text: " + prompt.getText());
        prompt.accept();
        assertEquals(driver.findElement(By.id("output")).getText(), value);
    }

    @Test
    public void iframe() {
        driver.findElement(By.id("btnConfirm")).click();
        Alert confirmBox = driver.switchTo().alert();
        System.out.println("Confirm box text: " + confirmBox.getText());
        confirmBox.accept();
        assertEquals(driver.findElement(By.id("output")).getText(),"Confirmed.");
        driver.findElement(By.id("btnConfirm")).click();
        confirmBox.dismiss();
        assertEquals(driver.findElement(By.id("output")).getText(),"Rejected!");
    }

    @Test
    public void openNewTab() {
        String tab = driver.getWindowHandle();
        driver.findElement(By.id("btnNewTab")).click();

        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        System.out.println("Tab text is: " + driver.findElement(By.id("new_tab_container")).getText());
        assertEquals(driver.findElement(By.id("new_tab_container")).getText(),"This is a new tab");

        driver.close();
        driver.switchTo().window(tab);
        System.out.println(driver.findElement(By.id("title")).getText());
        assertEquals(driver.findElement(By.id("title")).getText(), "Switch and Navigate");
    }

    @Test
    public void openNewWindow() {
        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(By.cssSelector("a[href='ex_switch_newWindow.html']")).click();

        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

        System.out.println("Tab text is: " + driver.findElement(By.id("new_window_container")).getText());
        assertEquals(driver.findElement(By.id("new_window_container")).getText(),"This is a new window");

        driver.close();
        driver.switchTo().window(winHandleBefore);
        assertEquals(driver.findElement(By.id("title")).getText(), "Switch and Navigate");
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }

}
