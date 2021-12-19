package Bonus.Reqres;

import io.qameta.allure.Step;
import static org.junit.Assert.*;

public class Validation {

    @Step("Verify int values")
    public static void verifyValues(int num1, int num2) {
        assertEquals(num1, num2);
    }
}
