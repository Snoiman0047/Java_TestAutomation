package PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClickPage {

    @FindBy(css = "input[type='button']")
    public WebElement btn_click;

}
