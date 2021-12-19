package Bonus.Reqres;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.concurrent.TimeUnit;

public class APIActions extends CommonOps{

    @Step("Get data from server")
    public static void get() {
        response = http_request.get(http_request_url + user_id);
        sleep_and_update_status_code();
    }

    @Step("POST request")
    public static void post() {
        http_request.header("Content-Type", "application/json");
        http_request.body(params.toJSONString());
        response = http_request.post(http_request_url);
        sleep_and_update_status_code();
    }

    @Step("PUT request")
    public static void put() {
        http_request.header("Content-Type", "application/json");
        http_request.body(params.toJSONString());
        response = http_request.put(http_request_url + user_id);
        sleep_and_update_status_code();
    }

    @Step("DELETE request")
    public static void delete(String id) {
        response = http_request.delete(http_request_url + id);
        sleep_and_update_status_code();
    }

    @Step("Wait")
    public static void sleep_and_update_status_code() {
        Uninterruptibles.sleepUninterruptibly(wait_time, TimeUnit.SECONDS);
        http_request_status_code = response.getStatusCode();
    }

}
