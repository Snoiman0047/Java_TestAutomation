package Bonus.Dog_API;

import io.qameta.allure.Step;
import io.restassured.RestAssured;

public class API_actions extends CommonOps {

    @Step("Get data from server")
    public static void get_all_breeds() {
        init_http_response();
        response = http_request.get(http_request_url + "/list/all");
        update_response_status_code(response.getStatusCode());
    }

    @Step("Get data from server")
    public static void get_all_by_breed(String breed, boolean random) {
        init_http_response();
        String path = http_request_url + "/" + breed + "/images";
        path += (random) ? "/random" : "";
        response = http_request.get(path);
        update_response_status_code(response.getStatusCode());
    }

    @Step("Random dog image")
    public static void random_image(int amount_to_random) {
        init_http_response();
        String path = http_request_url + "/image/random";
        path += (amount_to_random > 50 || amount_to_random < 0) ? "" : "/" + amount_to_random;
        response = http_request.get(path);
        update_response_status_code(response.getStatusCode());
    }

    @Step("Sub dogs breeds list")
    public static void sub_breed_list(String breed) {
        init_http_response();
        response = http_request.get(http_request_url + "/" + breed + "/list");
        update_response_status_code(response.getStatusCode());
    }

    @Step("Sub dogs breeds images list")
    public static void sub_breed_list_img(String breed, String sub_img, int amount_to_random) {
        init_http_response();
        String path = http_request_url + "/" + breed;
        path += (sub_img != "" && sub_img != null) ? "/" + sub_img + "/images" : "/images";
        path += (amount_to_random > 0 && amount_to_random <= 50) ? "/random/" + amount_to_random : (amount_to_random == 0) ? "/random" : "";
        response = http_request.get(path);
        update_response_status_code(response.getStatusCode());
    }


    @Step("Update response status code")
    private static void update_response_status_code(int status_code) {
        http_request_status_code = status_code;
    }

    @Step("Initialization response")
    private static void init_http_response() {
        http_request.header("Content-Type", "application/json");
        http_request = RestAssured.given();
    }
}
