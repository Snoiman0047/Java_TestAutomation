package Bonus.Dog_API;

import io.qameta.allure.Step;

public class DogWorkFlow extends CommonOps {

    @Step("print dogs breeds response")
    public static void get_all_breeds() {
        API_actions.get_all_breeds();
        print_response("GET ALL BREEDS request result");
    }

    @Step("print all dogs by breeds response")
    public static void get_all_by_breed(String breed, boolean random) {
        API_actions.get_all_by_breed(breed, random);
        print_response("GET ALL BY BREED request result");
    }

    @Step("random and print dog image response")
    public static void random_img(int amount_to_random) {
        API_actions.random_image(amount_to_random);
        print_response("RANDOM IMAGES request result");
    }

    @Step("sub breed list response")
    public static void sub_breed_list(String breed) {
        API_actions.sub_breed_list(breed);
        print_response("GET BY SUB BREED LIST request result");
    }

    @Step("sub breed images response")
    public static void sub_breed_img(String breed, String sub_id, int amount_to_random) {
        API_actions.sub_breed_list_img(breed, sub_id, amount_to_random);
        print_response("GET BY SUB BREED IMAGE request result");
    }


    @Step("Print result")
    public static void print_response(String header) {
        System.out.println("\n--  " + header + "  --");
        response.prettyPrint();
        System.out.println("\n");
    }

    @Step("verify response status code")
    public static void verify_response_status_code(int status_code) {
        Validation.verifyValues(http_request_status_code, status_code);
    }

}
