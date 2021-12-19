package Bonus.Reqres;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(className = "dark")
    private WebElement line_elem;

    public WebElement get_line_elem() {
        return line_elem;
    }
}
