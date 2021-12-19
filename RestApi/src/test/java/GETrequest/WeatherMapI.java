package GETrequest;


import org.junit.*;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherMapI {
    String url = "http://api.openweathermap.org/data/2.5/weather";
    String city = "Haifa,IL";
    String key = "9952dee360fd98281720531cc58e473b";

    private RequestSpecification httpRequest;
    private Response response;

    @BeforeClass
    public void startSession() {
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
    }

    @Test
    public void test01() {
        response = httpRequest.get("?units=metric&q=" + city + "&appid=" + key);
        printData();
        assertTrue(response.getContentType().contains("json"));
    }

    @Test
    public void test02() {
        response = httpRequest.get("?units=metric&q=" + city + "&appid=" + key + "&units=metric");
        printData();
        assertTrue(response.getContentType().contains("json"));
    }


    public void printData() {
        System.out.println(response.getBody().asString());
        System.out.println("Response Status Line: " + response.getStatusLine());
        System.out.println("Response Header: " + response.getHeader("Date"));
    }
}




