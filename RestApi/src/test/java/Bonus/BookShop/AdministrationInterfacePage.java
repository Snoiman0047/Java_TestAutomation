package Bonus.BookShop;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdministrationInterfacePage {

    @FindBy(xpath = "//li[@id='orders']/a")
    private WebElement link_orders;

    @Step
    public void navigateToOrdersPage() {
        link_orders.click();
    }

}
