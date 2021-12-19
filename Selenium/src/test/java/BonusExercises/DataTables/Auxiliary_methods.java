package BonusExercises.DataTables;
import io.qameta.allure.Step;

import java.util.List;

public class Auxiliary_methods {

    @Step("Print list")
    public static void print_list(List<String> list, String header) {
        System.out.println("\n--- " + header + " ---");
        list.forEach(value -> System.out.println(value));
    }

    @Step("Print List<List<String>>")
    public static void print_dup_list(List<List<String>> list, String header) {
        System.out.println("\n--- " + header + " ---");
        list.forEach(value_list -> value_list.forEach(value -> System.out.println(value)));
    }
}
