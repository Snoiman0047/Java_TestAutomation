package Bonus.Dog_API;

import io.qameta.allure.Step;
import net.bytebuddy.build.ToStringPlugin;
import static org.junit.Assert.*;


public class Validation {

    @Step("verify int values")
    public static void verifyValues(int value1, int value2) {
        assertEquals(value1, value2);
    }
}
