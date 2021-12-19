package Bonus.BookShop;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class OrdersPage {

    @FindBy(xpath = "//tbody/tr/td[5]")
    private List<WebElement> total_orders_price;


    @Step
    public double web_calcOrderPriceAmount() {
        return total_orders_price.stream().mapToDouble(elem -> Double.parseDouble((elem.getText()).substring(1))).sum();
    }

    @Step
    public double json_calcOrderPriceAmount(WebDriver driver) {
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='download_links']/a[3]")).click();
        List<String> orderPrices = json_getOrderPricesList();
        double sumOrderPrices = 0;
        for (String price : orderPrices) {
            sumOrderPrices += Double.parseDouble(price);
        }
        return sumOrderPrices;
    }

    @Step
    public List<String> json_getOrderPricesList() {
        RestAssured.baseURI = "http://localhost:8080/admin/orders.json";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();
        JsonPath jp = response.jsonPath();
        return jp.getList("total_price");
    }

    @Step
    public Dictionary<String, Double> getTotalAllOrdersPrice(WebDriver driver) {
        int num_page = 1;
        double web_total_price = 0, json_total_price = 0;
        while(driver.findElements(By.xpath("//a[@rel='next']")).size() != 0) {
            System.out.println(web_calcOrderPriceAmount() + " : " + json_calcOrderPriceAmount(driver));
            web_total_price += web_calcOrderPriceAmount();
            json_total_price +=  json_calcOrderPriceAmount(driver);
            System.out.println(web_total_price + " : " + json_total_price);
            System.out.println();
            backToOrdersPage(driver, ++num_page);
        }
        Dictionary<String, Double> total_prices = new Hashtable<>();
        total_prices.put("web_total_price", web_total_price);
        total_prices.put("json_total_price", json_total_price);
        return total_prices;
    }

    @Step
    public void backToOrdersPage(WebDriver driver, int num_page) {
        driver.navigate().to("http://localhost:8080/admin/orders?order=id_desc&page=" + num_page);
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
    }
}
