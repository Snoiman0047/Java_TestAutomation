package Bonus.BookShop;

import io.qameta.allure.Step;
import static org.junit.Assert.*;
public class Validation {

    @Step
    public static void verifyTotalPrice(double price1, double price2) {
        assertEquals(price1, price2);
    }
}
