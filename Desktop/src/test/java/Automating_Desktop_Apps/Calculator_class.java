package Automating_Desktop_Apps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Calculator_class {

    @FindBy(name = "Equals")
    private WebElement btn_equals;
    @FindBy(name = "Minus")
    private WebElement btn_minus;
    @FindBy(name = "Plus")
    private WebElement btn_plus;
    @FindBy(name = "Divide by")
    private WebElement btn_divide;
    @FindBy(name = "Multiply by")
    private WebElement btn_multiply;
    @FindBy(xpath = "//*[@AutomationId='clearButton']")
    private WebElement btn_clear;


    @FindBy(name = "Zero")
    private WebElement btn_zero;
    @FindBy(name = "One")
    private WebElement btn_one;
    @FindBy(name = "Two")
    private WebElement btn_two;
    @FindBy(name = "Three")
    private WebElement btn_three;
    @FindBy(name = "Four")
    private WebElement btn_four;
    @FindBy(name = "Five")
    private WebElement btn_five;
    @FindBy(name = "Six")
    private WebElement btn_six;
    @FindBy(name = "Seven")
    private WebElement btn_seven;
    @FindBy(name = "Eight")
    private WebElement btn_eight;
    @FindBy(name = "Nine")
    private WebElement btn_nine;

    @FindBy(xpath = "//*[@AutomationId='CalculatorResults']")
    private WebElement txt_calculator_result;


    @Step
    public void clear() {
        btn_clear.click();
    }
    @Step
    public void equal_action() {
        btn_equals.click();
    }
    @Step
    public void plus_action() {
        btn_plus.click();
    }
    @Step
    public void minus_action() {
        btn_minus.click();
    }
    @Step
    public void divide_action() {
        btn_divide.click();
    }
    @Step
    public void multiply_action() {
        btn_multiply.click();
    }

    @Step
    public void click_one() {
        btn_one.click();
    }
    @Step
    public void click_two() {
        btn_two.click();
    }
    @Step
    public void click_three() {
        btn_three.click();
    }
    @Step
    public void click_four() {
        btn_four.click();
    }
    @Step
    public void click_five() {
        btn_five.click();
    }
    @Step
    public void click_six() {
        btn_six.click();
    }
    @Step
    public void click_seven() {
        btn_seven.click();
    }
    @Step
    public void click_eight() {
        btn_eight.click();
    }
    @Step
    public void click_nine() {
        btn_nine.click();
    }
    @Step
    public void click_zero() {
        btn_zero.click();
    }


    @Step
    public String getCalculatorResultText() {
        return txt_calculator_result.getText().replace("Display is", "").trim();
    }

    @Step
    public void equals_and_verify(int number, String header) {
        equal_action();
        Validation.verify_values(String.valueOf(number), getCalculatorResultText(), header);
    }


}
