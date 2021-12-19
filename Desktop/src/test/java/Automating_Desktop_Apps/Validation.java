package Automating_Desktop_Apps;

import com.sun.org.glassfish.external.probe.provider.annotations.ProbeListener;
import io.qameta.allure.Step;

import static org.testng.Assert.*;

public class Validation {

    @Step
    public static void verify_values(String value1, String value2, String header) {
        assertEquals(value1, value2, "The " + header + " action does not work as expected");
    }
}
