package BonusExercises.Grafana;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;

@Listeners(Event_listeners.class)
public class Login_page {


    @FindBy(css = "div:nth-child(1) > div:nth-child(2) > div > div > input")
    private WebElement inp_user_name;

    @FindBy(id ="current-password")
    private WebElement inp_password;

    @FindBy(css = "form > button")
    private WebElement btn_login;

    @FindBy(css = "div > h1")
    private WebElement txt_title;


    @Step("Get alert message after login attempt")
    public String get_alert_message(WebDriver driver) {
        return driver.findElement(By.cssSelector("#reactRoot > div > main > div.page-alert-list > div > div > div > div.css-zmuccj > div")).getText();
    }

    @Step("Insert a username for the appropriate input")
    public void send_user_name(String user_name) {
        inp_user_name.sendKeys(user_name);
    }

    @Step("Insert a password for the appropriate input")
    public void send_password(String password) {
        inp_password.sendKeys(password);
    }

    @Step("Click on login button")
    public void login_action() {
        btn_login.click();
    }

}
