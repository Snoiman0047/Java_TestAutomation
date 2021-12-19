package BonusExercises.Grafana;

import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base {

    protected static WebDriver driver;

    protected final static String user_name = "admin", password = "admin";
    protected static String url, http_request_url = "http://localhost:3000/api/";
    protected static  String authorization_key = "";

    protected static Login_page _login;
    protected static Password_page _password;
    protected static Personal_area_page _private_area;
    protected static Configuration_page _configuration;

    protected static RequestSpecification http_request;
    protected static Response response;
    protected static JSONObject params = new JSONObject();
    protected static int http_request_status_code;
    protected static JsonPath jp;

}
