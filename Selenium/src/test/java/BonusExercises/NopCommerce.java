package BonusExercises;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NopCommerce {

    WebDriver driver;
    List<WebElement> products;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/camera-photo");
    }

    /**
     * Filter by price from low to high
     */
    @Test
    public void test_01(){
        Select drpSortBy = new Select(driver.findElement(By.id("products-orderby")));
        drpSortBy.selectByVisibleText((driver.findElement(By.xpath("//option[@value='11']"))).getText());
    }

    /**
     * Make sure there are 3 products in total
     */
    @Test
    public void test_02() {
        products = driver.findElements(By.className("item-box"));
        assertEquals(3, products.size(), "Unexpected products amount found");
    }

    /**
     * Make sure the product names match what we set in advance (Expected)
     */
    @Test
    public void test_03() throws Exception {
        List<String> productsName = (Arrays.asList(new String[] {"Nikon D5500 DSLR", "Leica T Mirrorless Digital Camera", "Apple iCam"}));
        products = driver.findElements(By.xpath("//h2[@class='product-title']/a"));
        contains(products, productsName);
    }

    /**
     * Make sure all products have a rating level of 3 stars or higher
     */
    @Test
    public void test_04() {
        products = driver.findElements(By.xpath("//div[@class='rating']/div"));
        assertTrue(check3starts(products));
    }


    private int getElementWidth(WebElement elem) {
        try {
            String widthStyle = elem.getAttribute("style");
            return  Integer.parseInt(widthStyle.substring(widthStyle.indexOf(' ')+1, widthStyle.length()-2));
        }
        catch (NullPointerException ex) {
            throw new NullPointerException("Element not founded");
        }
    }
    private boolean contains(List<WebElement> products, List<String> productsName) throws Exception {
        if (products.contains(productsName))
            return true;
        throw new Exception("Unexpected products amount found");
    }
    private boolean check3starts(List<WebElement> elems) {
        for (WebElement elem : elems) {
            if (getElementWidth(elem) < 60)
                return false;
        }
        return true;
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
