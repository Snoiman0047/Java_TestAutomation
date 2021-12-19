package ExecuteAppium;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;

import Listeners.EventListeners;
import java.util.concurrent.TimeUnit;

@Listeners(EventListeners.class)
public class LoginPage {


    @FindBy(xpath = "//*[@id='usernameTextField']")
    private WebElement txt_userName;

    @FindBy(xpath = "//*[@id='passwordTextField']")
    private WebElement txt_password;

    @FindBy(xpath = "//*[@text='Login']")
    private WebElement btn_login;


    @Step
    public void login(String userName, String password) {
        txt_userName.sendKeys(userName);
        txt_password.sendKeys(password);
        btn_login.click();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
    }

    @Step
    public static void verifyLogin(WebDriver driver) {
        Validation.verifyValues(driver.findElement(By.xpath("//*[@id='makePaymentButton']")).getText(), "Make Payment");
    }
}
