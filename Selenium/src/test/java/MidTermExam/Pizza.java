package MidTermExam;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;

@Listeners(PizzaSupport.class)
public class Pizza {

    WebDriver driver;
    String fName, lName;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/pizza/");
        fName = "Sara";
        lName = "Noiman";
    }

    @Test(description = "Verify price and insert names to match fields")
    public void test01() {
        verifyPrice(7.5);
    }

    @Test(description = "Change option, verify price, copy coupon code and submit file")
    public void test02() {
        selectOption();
        verifyPrice(10.5);
    }

    @Test(description = "Verify alert text")
    public void test03() {
        insertFullName(fName, lName);
        copyCouponCodeToComments(getCouponCode());
        submitOrder();
        verifyAlertText();
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }

    //region Steps

    @Step
    @Description("Verify price")
    public void verifyPrice(double price) {
        Assert.assertEquals(price, getPrice(), "Unexpected price");
    }

    @Step
    @Description("Get price")
    public double getPrice() {
        String price = driver.findElement(By.xpath("//span[@class='ginput_total ginput_total_5']")).getText();
        return Double.parseDouble(price.substring(1));
    }

    @Step
    @Description("Insert names to field")
    public void insertFullName(String fName, String lName) {
        driver.findElement(By.xpath("//input[@name='input_22.3']")).sendKeys(fName);
        driver.findElement(By.xpath("//input[@name='input_22.6']")).sendKeys(lName);
    }

    @Step
    @Description("Change default select option")
    public void selectOption() {
        Select drpOption = new Select(driver.findElement(By.xpath("//select[@id='input_5_21']")));
        drpOption.selectByVisibleText((driver.findElement(By.xpath("//option[@value='Delivery|3']"))).getText());
    }

    @Step
    @Description("Switch to iframe")
    public void switchToIframe(){
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
    }

    @Step
    @Description("Get coupon code")
    public String getCouponCode() {
        switchToIframe();
        String cupon = driver.findElement(By.xpath("//div[@id='coupon_Number']")).getText();
        driver.switchTo().defaultContent();
        return cupon;
    }

    @Step
    @Description("Copy coupon code into textarea")
    public void copyCouponCodeToComments(String cupon) {
        driver.findElement(By.xpath("//textarea[@name='input_20']")).sendKeys(cupon);
    }

    @Step
    @Description("Click to submit order")
    public void submitOrder(){
        driver.findElement(By.id("gform_submit_button_5")).click();
    }

    @Step
    @Description("Get alert text")
    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    @Step
    @Description("Verify alert text")
    public void verifyAlertText() {
        assertEquals(getAlertText(),  fName + ' ' + lName + ' ' + getCouponCode());
    }

    //endregion

}
