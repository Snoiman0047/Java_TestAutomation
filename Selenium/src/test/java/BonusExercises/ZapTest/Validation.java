package BonusExercises.ZapTest;

import io.qameta.allure.Step;
import static org.testng.Assert.*;

public class Validation {

    @Step("Verify String values")
    public static void verifyValues(String value1, String value2) {
        assertEquals(value1, value2);
    }

    @Step("Verify int values")
    public static void verifyValues(int value1, int value2) {
        assertEquals(value1, value2);
    }

    @Step("Verify double values")
    public static void verifyValues(double value1, double value2) {
        assertEquals(value1, value2);
    }

    @Step("Verify boolean values")
    public static void verifyValues(boolean value1, boolean value2) {
        assertEquals(value1, value2);
    }

}
