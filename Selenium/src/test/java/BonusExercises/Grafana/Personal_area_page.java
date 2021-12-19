package BonusExercises.Grafana;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.annotations.Listeners;

@Listeners(Event_listeners.class)
public class Personal_area_page {

    @FindBy(css = "span > img")
    private WebElement btn_user_actions_navigate;

    @FindBy(how = How.XPATH, using = "//*[@class='page-header__tabs']/ul/li[5]")
    private WebElement preferenceTab;

    @FindBy(css = "div.css-1cd0a4j.dropdown.dropup > ul > li:nth-child(4) > a > div")
    private WebElement btn_sign_out;


    @Step("Click to show user personal actions")
    public void show_user_actions(WebDriver driver) {
        btn_user_actions_navigate.click();
    }

    @Step("Click to sign out the site")
    public void sign_out() {
        Common_ops.sleep(1);
        btn_sign_out.click();
    }



    @FindBy(how = How.XPATH, using = "//*[@id=\"reactRoot\"]/div/main/div[3]/div/div[1]/div/div[2]/div/div[2]/form/fieldset/div[1]/div[2]/div/label[3]")
    private WebElement lightThemeBtn;

    @FindBy(how = How.XPATH, using = "//*[@id=\"reactRoot\"]/div/main/div[3]/div/div[1]/div/div[2]/div/div[2]/form/fieldset/div[1]/div[2]/div/label[2]")
    private WebElement darkThemeBtn;

    @FindBy(how = How.XPATH, using = "//*[@id=\"reactRoot\"]/div/main/div[3]/div/div[1]/div/div[2]/div/div[2]/form/fieldset/div[4]/button")
    private WebElement saveChangesBtn;

    @FindBy(how = How.XPATH, using = "//*[@class='page-container page-body']")
    private WebElement pageContainer;

    private void switchToLightMode(){
        this.lightThemeBtn.click();
    }

    private void switchTodarktMode(){
        this.darkThemeBtn.click();
    }

    private void saveChanges(){
        this.saveChangesBtn.click();
    }

    public String getHexColor(WebDriver driver){
        String color = pageContainer.getCssValue("background-color");
        String hex = Color.fromString(color).asHex();
        return hex;
    }

    public void changeToLightBackgroundColor(){
        switchToLightMode();
        saveChanges();
    }

    public void changeToDarkBackgroundColor(){
        switchTodarktMode();
        saveChanges();
    }

}
