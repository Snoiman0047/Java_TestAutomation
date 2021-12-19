package Bonus.Reqres;
import io.qameta.allure.Step;

public class HomeWorkFlows extends CommonOps{

    @Step("Get user by ID")
    public static void get_user_by_ID() {
        APIActions.get();
        print_response("GET request result");
    }

    @Step("Delete user by ID")
    public static void delete_user_by_ID(String user_id) {
        APIActions.delete(user_id);
        print_response("DELETE request result");
    }

    @Step("Add user")
    public static void add_user() {
        APIActions.post();
        print_response("POST request result");
    }

    @Step("Update user")
    public static void update_user() {
        APIActions.put();
        print_response("PUT request result");
    }

    @Step("Print result")
    public static void print_response(String header) {
        System.out.println("--  " + header + "  --");
        response.prettyPrint();
        System.out.println("\n");
    }

}
