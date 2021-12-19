package Bonus.Reqres;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;


public class Base {

    // General
    protected static WebDriver driver;
    protected static int wait_time = 3;
    protected static String url, http_request_url = "/api/users/", user_id;
    final protected static String screenshot_path = "C:\\Users\\snoim\\OneDrive\\Desktop\\Automation\\QA Automation\\TestAutomation\\RestApi\\src\\test\\java\\Bonus\\Reqres\\Screenshots\\";

    // Rest API
    protected static RequestSpecification http_request;
    protected static Response response;
    protected static JSONObject params = new JSONObject();
    protected static int http_request_status_code;

    // ReqresIO API
    protected static HomePage home;

}
