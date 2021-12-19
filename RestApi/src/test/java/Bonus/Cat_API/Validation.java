package Bonus.Cat_API;

import io.qameta.allure.Step;
import static org.junit.Assert.*;

public class Validation {

    @Step
    public static void verifyValues(int value1, int value2) {
        assertEquals(value1, value2);
    }

    @Step
    public static void verifyValues(boolean condition, boolean expected_result){
        if(expected_result)
            assertTrue(condition);
        else assertFalse(condition);
    }
}
