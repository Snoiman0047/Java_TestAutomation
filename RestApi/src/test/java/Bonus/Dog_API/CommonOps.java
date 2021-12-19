package Bonus.Dog_API;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class CommonOps extends Base {

    @BeforeClass
    public static void startUp() {
        set_url("https://dog.ceo/dog-api/");
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
    }

}
