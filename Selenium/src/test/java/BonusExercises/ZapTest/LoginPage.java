package BonusExercises.ZapTest;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {


    @FindBy(xpath = "//*[@id='page-top']/app/header/div/div/div/div/ng-component/div/form/div[1]/input")
    private WebElement inp_user_name;

    @FindBy(css = "#page-top > app > header > div > div > div > div > ng-component > div > form > div:nth-child(2) > input")
    private WebElement inp_password;

    @FindBy(xpath = "//*[@id='page-top']/app/header/div/div/div/div/ng-component/div/form/div[3]/button")
    private WebElement btn_login;

    @Step
    public void send_user_name(String user_name) {
        inp_user_name.sendKeys(user_name);
    }

    @Step
    public void send_password(String password) {
        inp_password.sendKeys(password);
    }

    @Step
    public void login_action() {
        btn_login.click();
    }



}
