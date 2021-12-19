package POST_PUT_DELETE;

import java.util.Arrays;
import java.util.List;
import io.qameta.allure.Step;
import org.json.simple.JSONObject;
import org.junit.*;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class Student {
    String baseURL = "http://localhost:9000";
    RequestSpecification request;
    Response response;
    JSONObject requestParams;
    RequestSpecification httpRequest;

    @BeforeClass
    public void startSession() {
        RestAssured.baseURI = baseURL;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        httpRequest = RestAssured.given();
        requestParams = new JSONObject();
    }

    @Test
    public void test01_withoutCoursesList() {
        createJSONObject("Sima", "Cohen", "s@cohen.com", "QA automation");
        sendData();
        printData();
        verifyStatusCode(201);
    }

    @Test
    public void test02_withCoursesList() {
        createJSONObject("Naama", "Levi", "n@levi.com", "Software developer");
        addCoursesToJSONObject(Arrays.asList("Python Course", "CSharp Course", "Java Course"));
        sendData();
        printData();
        verifyStatusCode(201);
    }

    @Test
    public void test03_putMethod() {
        createJSONObject("Sima", "Cohen", "s@cohen.com", "Physics");
        response = httpRequest.put("/student/101");
        printData();
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void test04_deleteMethod() {
        response = httpRequest.delete("/student/101");
        printData();
        verifyStatusCode(204);
    }


    @Step
    public void createJSONObject(String fName, String lName, String email, String programme) {
        requestParams.put("firstName", fName);
        requestParams.put("lastName", lName);
        requestParams.put("email", email);
        requestParams.put("programme", programme);
    }

    @Step
    public void addCoursesToJSONObject(List<String> courses) {
        requestParams.put("courses", courses);
    }

    @Step
    public void sendData() {
        request.body(requestParams.toJSONString());
        response = request.post("/student");
    }

    @Step
    public void printData() {
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Step
    public void verifyStatusCode(int statusCode) {
        assertEquals(response.getStatusCode(), statusCode);

    }

}
