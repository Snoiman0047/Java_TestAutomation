package GETrequest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeaterMapIII {
    String url = "http://api.openweathermap.org/data/2.5/weather";
    String city = "Jerusalem,IL";
    String key = "9952dee360fd98281720531cc58e473b";

    RequestSpecification httpRequest;
    Response response;
    JsonPath jp;
    String humidityAPI, humidityWeb;
     WebDriver driver;

    @BeforeClass
    public void start() {
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
    }

    @Test
    public void test01() {
        response = httpRequest.get("?units=metric&q=" + city + "&appid=" + key);
        jp = response.jsonPath();
        System.out.println(jp.get("sys.country").toString());
        assertEquals(jp.get("sys.country"), "IL");
    }

    @Test
    public void test02() {
        humidityAPI = jp.getString("main.humidity");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://openweathermap.org/");
        verifyHumidity();
    }

    public void verifyHumidity(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Weather in your city']")));
            driver.findElement(By.cssSelector("input[placeholder='Weather in your city']")).sendKeys("Jerusalem,IL");
            driver.findElement(By.cssSelector("input[placeholder='Weather in your city']")).sendKeys(Keys.RETURN);
            driver.findElement(By.partialLinkText("Jerusalem, IL")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("current-container")));
            humidityWeb = driver.findElement(By.xpath("//*[@id='weather-widget']/div[2]/div[1]/div[1]/div[2]/ul/li[3]")).getText().split("\n")[1];
            assertEquals(humidityAPI + "%", humidityWeb);
        }
        catch(Exception e) {
            System.out.println("Test failed: " + e);
            fail("Test failed: " + e);
        }
        finally {
            driver.quit();
        }
    }
}
