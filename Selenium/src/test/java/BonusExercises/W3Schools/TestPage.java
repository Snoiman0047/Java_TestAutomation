package BonusExercises.W3Schools;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

@Listeners(EventListeners.class)
public class TestPage {

    WebDriver driver;
    TablePage table;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        table = PageFactory.initElements(driver, TablePage.class);
    }

    @Test
    public void test01() {
        ValidationPage.verifyValues(table.getNumberOfRowsInTable(), 7);
    }

    @Test
    public void test02() {
        ValidationPage.verifyValues(table.getNumberOfColumnInTable(), 3);
    }

    @Test
    public void test03() throws IOException {
        printList("Countries in Europe", table.getCountriesTableInEuropeList());
        String path = "C:\\Users\\snoim\\OneDrive\\Desktop\\Automation\\QA Automation\\TestAutomation\\Selenium\\src\\test\\java\\BonusExercises\\W3Schools\\data.csv";
        table.writeTableToCSV(path);
        ValidationPage.verifyLists(table.convertTableToList(), CSV.readCSV(path));
    }

    @Step
    public void printList(String header, List<String> list) {
        System.out.print(header + ": ");
        list.forEach((value) -> {
            System.out.print(value + ", ");
        });
        System.out.println();
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}

