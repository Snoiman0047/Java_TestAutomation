package AssertsAndVerifications;

import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;


public class CalculateBMI {

    WebDriver driver;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/bmi");
    }

    @Test
    public void checkBMI_01() {
        driver.findElement(By.id("weight")).sendKeys("63");
        driver.findElement(By.name("height")).sendKeys("160");
        driver.findElement(By.id("calculate_data")).click();
        String expectedResult = "25";
        String actualResult = driver.findElement(By.id("bmi_result")).getAttribute("value");
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void exploreBMI_02() {
        int width = driver.findElement(By.id("calculate_data")).getSize().getWidth();
        int height = driver.findElement(By.id("calculate_data")).getSize().getHeight();
        int XCoordinate = driver.findElement(By.id("calculate_data")).getLocation().x;
        int YCoordinate = driver.findElement(By.id("calculate_data")).getLocation().y;

        System.out.println("Button width: " + width);
        System.out.println("Button height: " + height);
        System.out.println("Button (x, y) coordinate: (" + XCoordinate + "," + YCoordinate + ")");

        assertTrue(driver.findElement(By.id("calculate_data")).isDisplayed());
        assertTrue(driver.findElement(By.id("calculate_data")).isEnabled());
        assertFalse(driver.findElement(By.id("calculate_data")).isSelected());

        assertEquals(driver.findElement(By.id("calculate_data")).getTagName(),"input");
        assertEquals(driver.findElement(By.id("calculate_data")).getAttribute("value"),"Calculate BMI");

        assertFalse(driver.findElement(By.id("new_input")).isDisplayed(),"Checking Element (new_input) is Displayed");
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
