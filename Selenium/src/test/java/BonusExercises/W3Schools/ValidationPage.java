package BonusExercises.W3Schools;

import io.qameta.allure.Step;

import java.util.List;

import static org.testng.Assert.*;


public class ValidationPage {

    @Step
    public static void verifyValues(int num1, int num2) {
        assertEquals(num1, num2);
    }

    @Step
    public static void verifyLists(List<List<String>> list1, List<List<String>> list2) {
        assertTrue(list1.equals(list2));
    }
}
