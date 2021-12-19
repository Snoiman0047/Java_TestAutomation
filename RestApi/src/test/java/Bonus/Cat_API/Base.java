package Bonus.Cat_API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class Base {

    protected static int integer;
    protected static String _url;
    protected final static String key = "36a1fbcf-c4fd-42f2-a30a-6aff786d7fcd";
    protected final static String http_request_url = "https://api.thecatapi.com/v1/";
    protected static int status_code;

    // Rest API
    protected static RequestSpecification http_request;
    protected static Response response;
    protected static int http_request_status_code;
    protected static JSONObject params;
    protected static JsonPath jp;
}
