package ExecuteAppium;

import io.qameta.allure.Step;
import static org.testng.Assert.*;

public class Validation {

    @Step
    public static void verifyValues(String value1, String value2){
        assertEquals(value1, value2);
    }

    @Step
    public static void verifyValues(int value1, int value2){
        assertEquals(value1, value2);
    }
}
