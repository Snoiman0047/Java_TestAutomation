package ExecuteAppium;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class PaymentPage {

    @FindBy(xpath = "//*[@id='phoneTextField']")
    private WebElement txt_phone;

    @FindBy(xpath = "//*[@id='nameTextField']")
    private WebElement txt_name;

    @FindBy(xpath = "//*[@id='amountTextField']")
    private WebElement txt_amountToPay;

    @FindBy(xpath = "//*[@id='countryTextField']")
    private WebElement txt_country;

    @FindBy(xpath = "//*[@id='sendPaymentButton']")
    private WebElement btn_sendPayment;

    @Step
    public void makeAndSendPayment(String phone, String name, double amountToPay, String country) {
        txt_phone.sendKeys(phone);
        txt_name.sendKeys(name);
        txt_amountToPay.sendKeys(String.valueOf(amountToPay));
        txt_country.sendKeys(country);
        btn_sendPayment.click();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
    }

    @Step
    public void verifyPaymentDone(WebDriver driver) {
        LoginPage.verifyLogin(driver);
    }

}
