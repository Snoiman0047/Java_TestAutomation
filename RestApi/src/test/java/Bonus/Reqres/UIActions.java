package Bonus.Reqres;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class UIActions extends Base {

    @Step("scroll into view")
    public static void scroll_into_view(WebElement element) {
        Uninterruptibles.sleepUninterruptibly(wait_time, TimeUnit.SECONDS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}