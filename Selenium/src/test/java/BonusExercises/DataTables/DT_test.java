package BonusExercises.DataTables;

import BonusExercises.W3Schools.EventListeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.util.List;


@Listeners(EventListeners.class)
public class DT_test {

    WebDriver driver;
    Data_table_block web_table;
    String city, profession;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://datatables.net/");
        web_table = PageFactory.initElements(driver, Data_table_block.class);
    }

    @Test
    public void test_it() {

        // Print number of rows and columns in the web table
        web_table.print_table_data();

        // Print name and age of New Yorkers in first page
        city = "New York";
        Auxiliary_methods.print_list(web_table.get_people_name_and_age_in_city(city), "New Yorkers in page");

        // Print name and age of New Yorkers in all pages
        Auxiliary_methods.print_dup_list(web_table.get_all_people_name_and_age_in_city(city), "All New Yorkers");

        // Print salary ang of all professionals
        profession = "Software Engineer";
        web_table.init_table();
        System.out.println("\n" + profession + " salary avg: " + web_table.get_all_salary_avg_of_profession(profession));

        // Verify that all New Yorkers are over 22 years
        web_table.init_table();
        web_table.check_all_people_in_city_over_x_years(city, 22);
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }

}
