package BonusExercises;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Support {

    //region Loudev

    public static void verifyElements(WebDriver driver) {
        List<WebElement> options = driver.findElements(By.xpath("//div[@id='ms-aloha']/div[1]/ul/li[@class='ms-elem-selectable']/span"));
        options.forEach((op) -> {assertTrue(isNumberInRange(convertStringToInteger(op.getText())));});
    }

    private static int convertStringToInteger(String str) {
        return Integer.parseInt(str.substring(str.indexOf(" ") + 1));
    }
    private static boolean isNumberInRange(int num) {
        return num >= 3 && num <= 20;
    }

    //endregion

    //region SuperCalculatorVI

    public static List<WebElement> dupCalculation(WebDriver driver, int num) {
        changeSelectOption(driver, new Select(driver.findElement(By.tagName("select"))));
        dupCalcRecursive(getCalcButton(driver), getNumerator(driver), getDenominator(driver), 1, 1, num);
        return createResultList(driver);
    }
    public static void printTextWebElementList(List<WebElement> elems) {
        System.out.println("List element:");
        elems.forEach((elem) -> {
            System.out.println(elem.getText());
        });
    }

    private static void changeSelectOption(WebDriver driver, Select elem) {
        elem.selectByVisibleText((driver.findElement(By.xpath("//option[@value='MULTIPLICATION']"))).getText());
    }

    private static WebElement getCalcButton(WebDriver driver) {
        return driver.findElement(By.id("gobutton"));
    }
    private static WebElement getNumerator(WebDriver driver) { return driver.findElement(By.xpath("//input[@ng-model='first']")); }
    private static WebElement getDenominator(WebDriver driver) { return driver.findElement(By.xpath("//input[@ng-model='second']")); }

    private static void dupCalcRecursive(WebElement calcButton, WebElement numerator, WebElement denominator, int num, int den, int dup) {
        if (num == dup && den == (dup + 1))
            return;
        if (den > dup) {
            den = 1;
            ++num;
        }
        calcIt(calcButton, numerator, denominator, num, den);
        wait(1850);
        dupCalcRecursive(calcButton, numerator, denominator, num, ++den,  dup);
    }
    private static void calcIt(WebElement calcButton, WebElement numerator, WebElement denominator, int num, int den) {
        numerator.sendKeys(Integer.toString(num));
        denominator.sendKeys(Integer.toString(den));
        calcButton.click();
    }
    private static void wait(int milliSeconds) {
        Uninterruptibles.sleepUninterruptibly(milliSeconds, TimeUnit.MILLISECONDS);
    }

    private static List<WebElement> createResultList(WebDriver driver) {
        return driver.findElements(By.xpath("//tbody/tr/td[3]"));
    }

    //endregion

    //region SuperCalculatorVII

    public static int random(WebDriver driver) {
        switchToIframe(driver);
        changeToMaxRandom(driver, "10");
        driver.findElement(By.cssSelector("input[id$='button']")).click();
        wait(600);
        return convertStringToInteger(driver.findElement(By.cssSelector("span[style='font-size:100%;font-weight:bold;']")));
    }
    public static void navigateToNewTab(WebDriver driver, String url) {
        String tab = driver.getWindowHandle();
        driver.get(url);
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.switchTo().window(tab);
    }
    public static void print(int rand, int sum) {
        System.out.println("The random number is: " + rand);
        System.out.println("The result for number " + rand + " is: " + sum);
    }

    private static void switchToIframe(WebDriver driver) {
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
    }
    private static void changeToMaxRandom(WebDriver driver, String num) {
        WebElement input = driver.findElement(By.cssSelector("input[id$='max']"));
        input.clear();
        input.sendKeys(num);
    }

    private static int convertStringToInteger(WebElement elem) {
        return Integer.parseInt(elem.getText());
    }

    public static int sumOfNumberMultiBySmallerDigits(WebDriver driver, int num) {
        WebElement calcButton = driver.findElement(By.id("gobutton"));
        WebElement numerator  = driver.findElement(By.xpath("//input[@ng-model='first']"));
        WebElement denominator = driver.findElement(By.xpath("//input[@ng-model='second']"));

        changeSelectOption(driver, new Select(driver.findElement(By.tagName("select"))));
        for(int i = num-1; i >= 0; i--) {
            calcIt(calcButton, numerator, denominator, num, i);
            Uninterruptibles.sleepUninterruptibly(1850, TimeUnit.MILLISECONDS);
        }
        return sumResultList(createResultList(driver));
    }
    private static int sumResultList(List<WebElement> elems) {
        return elems.stream().mapToInt(Support::convertStringToInteger).sum();
    }

    //endregion

}
