package PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormPage {

    @FindBy(id = "occupation")
    private WebElement txt_occupation;

    @FindBy(id = "age")
    private WebElement txt_age;

    @FindBy(id = "location")
    private WebElement txt_location;


    public void action(String occupation, String age, String location) {
        txt_occupation.sendKeys(occupation);
        txt_age.sendKeys(age);
        txt_location.sendKeys(location);
    }
}
