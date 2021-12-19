package WebDriverMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class WebTable {

    WebDriver driver;
    List<WebElement> structure;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.techlistic.com/p/demo-selenium-practice.html");
    }

    /**
     * Verify that there are only 4 structure values present.
     */
    @Test
    public void test_01() {

        /* Read the 'Structure' column and find out the total number of structures present. */
        structure = (driver.findElement(By.xpath("//tbody[1]"))).findElements(By.tagName("tr"));
        System.out.println("columns: " + structure.size());

        /* Read the value of 'Total' column and match with the previous step. */
        WebElement total = driver.findElement(By.xpath("//td[@colspan='7']"));
        int size = Integer.parseInt(total.getText().substring(0, total.getText().indexOf(" ")));
        Assert.assertEquals(size, structure.size());
    }

    /**
     * Read All the Values from the table row-wise and print them all
     */
    @Test
    public void test_02() {
        int index = 1;
        for(WebElement row: structure) {
            System.out.print("column " + (index++) + "   " + (row.findElement(By.tagName("th"))).getText() + ": ");
            for(WebElement elem: row.findElements(By.tagName("td"))) {
                System.out.print(elem.getText() + ", ");
            }
            System.out.println();
        }
    }

    /**
     * Verify that Burj Khalifa has a height of 829m (similar for other structures)
     */
    @Test
    public void test_03() {
        for(WebElement row: structure) {
            if (((row.findElement(By.tagName("th"))).getText()).equalsIgnoreCase("Burj Khalifa")) {
                Assert.assertEquals("829m", (row.findElement(By.xpath("//td[3]")).getText()));
            }
        }
    }

    /**
     * Verify that 6th row (Last Row) has only two columns.
     */
    @Test
    public void test_04() {
        structure = driver.findElements(By.xpath("//tfoot/tr/*"));
        Assert.assertEquals(2, structure.size());
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}

