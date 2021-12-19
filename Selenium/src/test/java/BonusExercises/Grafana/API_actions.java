package BonusExercises.Grafana;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.qameta.allure.Step;
import io.restassured.RestAssured;

public class API_actions extends Common_ops{

    @Step("Get data from server")
    public static void GET_request_by_login_or_email(String login_or_email) {
        init_http_response();
        response = http_request.get(http_request_url + "users/lookup?loginOrEmail=" + login_or_email);
        update_response_status_code(response.getStatusCode());
    }

    @Step
    public static void PUT_request_change_password(String old_password, String new_password) {
        params.put("oldPassword", old_password);
        params.put("newPassword", new_password);
        init_http_response();
        http_request.body(params.toJSONString());
        response = http_request.put(http_request_url + "user/password");
        update_response_status_code(response.getStatusCode());
    }

    @Step
    public static void POST_revoke_auth_token_of_user(int auth_token_ID) {
        params.put("authTokenId", auth_token_ID);
        init_http_response();
        http_request.header("Authorization", "Bearer eyJrIjoiT0tTcG1pUlY2RnVKZTFVaDFsNFZXdE9ZWmNrMkZYbk");
        http_request.body(params.toJSONString());
        response = http_request.post(http_request_url + "user/revoke-auth-token");
        update_response_status_code(response.getStatusCode());
    }

    @Step("Initialization response")
    private static void init_http_response() {
        http_request = RestAssured.given();
        http_request.header("Content-Type", "application/json");
        http_request.header("Accept", "application/json");
        http_request.header("Authorization", "Basic YWRtaW46YWRtaW4=");
    }

    @Step("Update response status code")
    private static void update_response_status_code(int status_code) {
        http_request_status_code = status_code;
    }
}
