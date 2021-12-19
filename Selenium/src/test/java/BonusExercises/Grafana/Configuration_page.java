package BonusExercises.Grafana;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class Configuration_page {

    @FindBy(how = How.XPATH, using = "//*[@data-testid='top-section-items']//*[@class='css-1pe5hie dropdown'][5]")
    private WebElement configurationBtn;


    /**
     * Plugin
     */

    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div[3]/div/div[1]/div/div[1]/div/div/nav/div[2]/ul/li[4]/a")
    public WebElement Plug;
    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div[3]/div/div[1]/div/div[2]/div/div/div[1]/div[2]/div[2]/div/label[1]")
    public WebElement SelectAll;
    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div[3]/div/div[1]/div/div[2]/div/div/div[1]/div[1]/div/input")
    public WebElement SearchBox;

    @Step
    public void goToPlugins() {
        configurationBtn.click();
        Plug.click();
    }
    @Step
    public void Search(String s) {
        SelectAll.click();
        SearchBox.sendKeys(s);
    }


    /**
     * Users
     */

    @FindBy(xpath = "//*[@id='reactRoot']/div/main/div[3]/div/div[1]/div/div[1]/div/div/nav/div[2]/ul/li[2]/a/div")
    private WebElement btn_users;

    public void verify_users(WebDriver driver, String user, String email) {
        btn_users.click();
        Common_ops.sleep(1);
        Assert.assertTrue(driver.findElement(By.cssSelector("span[title='admin']")).getText().equals(user) &&
                driver.findElement(By.cssSelector("span[title='admin@localhost']")).getText().equals(email));
    }
}
