package Bonus.Cat_API;


import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.testng.annotations.*;

public class CommonOps extends Base {

    @BeforeClass
    public static void startUp() {
        set_url("https://thecatapi.com/");
        init_API();
    }

    @Step("Update url")
    public static void set_url(String url) {
        _url = url;
    }

    @Step("Initialization API")
    public static void init_API() {
        RestAssured.baseURI = _url;
        http_request = RestAssured.given();
        http_request.header("x-api-key", key);
    }
}
