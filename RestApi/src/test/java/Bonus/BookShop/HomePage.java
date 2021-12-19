package Bonus.BookShop;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(xpath = "//div[@id='auth']/a[1]")
    private WebElement link_administration_interface;

    @Step
    public void navigateToAdministrationInterface() {
        link_administration_interface.click();
    }
}
