package Bonus.BookShop;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.util.Dictionary;
import java.util.concurrent.TimeUnit;


public class BookShopTest {

    Document doc;
    WebDriver driver;
    String url = "http://localhost:8080/";

    double web_order_prices, json_order_price;
    HomePage home;
    AdministrationInterfacePage administration_Interface;
    OrdersPage orders;

    @BeforeClass
    public void startSession() throws IOException {
        doc = Jsoup.connect(url).get();
    }

    @Test
    public void test01_verifyBooksAmount() {
        assertEquals(doc.getElementsByClass("product").size(), 33);
    }

    @Test
    public void test02_verifyOrderPrices_currentPage() {
        openDriver();
        navigateToOrdersPage();
        web_order_prices = orders.web_calcOrderPriceAmount();
        json_order_price = orders.json_calcOrderPriceAmount(driver);
        Validation.verifyTotalPrice(web_order_prices, json_order_price);
    }

    @Test
    public void test03_verifyOrderPrices_allPages() {
        web_order_prices = json_order_price = 0;
        driver.navigate().to("http://localhost:8080/admin/orders?order=id_desc&page=1");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        Dictionary<String, Double> total_prices =  orders.getTotalAllOrdersPrice(driver);
        Validation.verifyTotalPrice(total_prices.get("web_total_price"), total_prices.get("json_total_price"));
    }

    @Step
    public void openDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

        home = PageFactory.initElements(driver, HomePage.class);
        administration_Interface = PageFactory.initElements(driver, AdministrationInterfacePage.class);
        orders = PageFactory.initElements(driver, OrdersPage.class);
    }

    @Step
    public void navigateToOrdersPage() {
        home.navigateToAdministrationInterface();
        administration_Interface.navigateToOrdersPage();
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

}
