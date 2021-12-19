package DataDrivenTesting;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.org.glassfish.gmbal.Description;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class DDTcsv {

    WebDriver driver;
    static String ddtPath = "C:/Users/snoim/OneDrive/Desktop/Automation/QA Automation/TestAutomation/Selenium/src/test/java/DataDrivenTesting/csv_file.txt";

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://wikipedia.org/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(dataProvider = "data-provider")
    public void testIt(String key, String value) {
        driver.findElement(By.id("searchInput")).sendKeys(key);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        assertEquals(driver.findElement(By.id("firstHeading")).getText(), value);
        driver.get("http://wikipedia.org/");
    }

    @DataProvider(name = "data-provider")
    @Description("DataProvider: Get Object 'table' from CSV file")
    public static Object[][] getDataObject() {
        return getDataFromCSV(ddtPath);
    }

    @Description("Convert CSV to Object 'table'")
    public static Object[][] getDataFromCSV(String filePath) {
        List<List<String>> csvData = readCSV(filePath);
        int rowSize = csvData.size();
        int columnSize = csvData.get(0).size();
        Object[][] data = new Object[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                data[i][j] = csvData.get(i).get(j);
            }
        }
        return data;
    }

    @Description("Read CSV from file path")
    public static List<List<String>> readCSV(String filePath) {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return records;
    }

//    @AfterClass
//    public void closeSession() {
//        driver.quit();
//    }
}
