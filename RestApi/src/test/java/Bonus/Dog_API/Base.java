package Bonus.Dog_API;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

public class Base {

    protected static WebDriver driver;
    protected static int wait_time = 3;
    protected static String _url;
    protected static String http_request_url = "https://dog.ceo/api/breeds";


    // Rest API
    protected static RequestSpecification http_request;
    protected static Response response;
    protected static JSONObject params = new JSONObject();
    protected static int http_request_status_code;

}
