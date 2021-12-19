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
public class PersonalAreaActionsPage {

    @FindBy(css = "*[text*='balance']")
    private WebElement txt_balance;

    @FindBy(xpath = "//*[@id='makePaymentButton']")
    private WebElement btn_makePayment;

    @FindBy(xpath = "//*[@id='mortageRequestButton']")
    private WebElement btn_mortgageRequest;

    @FindBy(xpath = "//*[@id='expenseReportButton']")
    private WebElement btn_expenseReport;

    @FindBy(xpath = "//*[@id='logoutButton']")
    private WebElement btn_logOut;


    @Step
    public void makePayment() {
        btn_makePayment.click();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
    }

    @Step
    public void mortgageRequest() {
        btn_mortgageRequest.click();
    }

    @Step
    public void expenseReport() {
        btn_expenseReport.click();
    }

    @Step
    public void logOut() {
        btn_logOut.click();
    }

    @Step
    public void verifyMakePaymentPage(WebDriver driver) {
        Validation.verifyValues(driver.findElement(By.id("sendPaymentButton")).getText(), "Send Payment");
    }

    @Step
    public double getBalance() {
        return Double.parseDouble(txt_balance.getText().substring(17, txt_balance.getText().length()-1));
    }

    @Step
    public void verifyBalance(double balance, double paymentAmount) {
//        Validation.verifyValues(balance - paymentAmount, getBalance());
    }

    @Step
    public void verifyLogOut(WebDriver driver) {
        Validation.verifyValues(driver.findElement(By.xpath("//*[@id='loginButton']")).getText(), "Login");
    }

}
