package PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(name = "username2")
    private WebElement txt_userName;

    @FindBy(name = "password2")
    private WebElement txt_password;

    @FindBy(id = "submit")
    private WebElement btn_submit;

    public void action(String userName, String password) {
        txt_userName.sendKeys(userName);
        txt_password.sendKeys(password);
        btn_submit.click();
    }
}
