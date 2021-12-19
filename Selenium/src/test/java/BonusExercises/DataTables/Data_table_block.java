package BonusExercises.DataTables;

import BonusExercises.W3Schools.EventListeners;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

@Listeners(EventListeners.class)
public class Data_table_block {

    @FindBy(xpath = "//*[@id='example']/thead/tr")
    private List<WebElement> web_headers;

    @FindBy(xpath = "//*[@id='example']/tbody/tr")
    private List<WebElement> web_table;

    @FindBy(xpath ="//*[@id='example_next']")
    private WebElement btn_next;

    @FindBy(xpath ="//*[@id='example_previous']")
    private WebElement btn_previous;



    @Step("Get table rows")
    public int get_table_rows() {
        return web_table.size();
    }

    @Step("Get table columns")
    public int get_table_columns() {
        return web_table.get(0).findElements(By.tagName("td")).size();
    }

    @Step("Get table data")
    public String get_table_data() {
        return "Web table { rows = " + get_table_rows() + ", columns = " + get_table_columns() + " }";
    }

    @Step("Print table data")
    public void print_table_data() {
        System.out.println(get_table_data());
    }

    @Step("Get name and age of city residents")
    public List<String> get_people_name_and_age_in_city(String city) {
        List<String> peoples = new ArrayList<>();
        for (int i = 0; i < web_table.size(); i++) {
            List<WebElement> elem = web_table.get(i).findElements(By.tagName("td"));
            if (elem.get(2).getText().equals(city)) {
                peoples.add("people : { name = " + elem.get(0).getText() + ", age = " + elem.get(3).getText() + " } ;");
            }
        }
        return peoples;
    }

    @Step("Get all name and age of city residents")
    public List<List<String>> get_all_people_name_and_age_in_city(String city) {
        List<List<String>> peoples = new ArrayList<>();
        while(btn_next.getAttribute("tabindex").equals("0")) {
            peoples.add(get_people_name_and_age_in_city(city));
            next_click();
        }
        peoples.add(get_people_name_and_age_in_city(city));
        return peoples;
    }

    @Step("Check if all city residents are above a certain age")
    public void check_all_people_in_city_over_x_years(String city, int age) {
        SoftAssert sa = new SoftAssert();
        int index = 1;
        while(btn_next.getAttribute("tabindex").equals("0")) {
            sa.assertTrue(check_new_yorkers_over_x_years(city, age), "Error in page " + index);
            next_click();
            index++;
        }
        sa.assertTrue(check_new_yorkers_over_x_years(city, age), "page " + index);
        sa.assertAll();
    }

    @Step("Check if city residents are above a certain age")
    public boolean check_new_yorkers_over_x_years(String city, int age) {
        for (int i = 0; i < web_table.size(); i++) {
            List<WebElement> elem = web_table.get(i).findElements(By.tagName("td"));
            if (elem.get(2).getText().equals(city) && !elem.get(3).getText().equals(String.valueOf(age))) {
                return false;
            }
        }
        return true;
    }

    @Step("Get profession salary avg")
    public double get_salary_avg_of_profession(String profession) {
        double professional_counter = 0, sum_salary = 0;
        for (int i = 0; i < web_table.size(); i++) {
            List<WebElement> elem = web_table.get(i).findElements(By.tagName("td"));
            if (elem.get(1).getText().equals(profession)) {
                professional_counter++;
                sum_salary += convert_string_to_double(elem.get(5).getAttribute("textContent"));
            }
        }
        if (sum_salary == 0 || professional_counter == 0)
            return 0;
        return sum_salary / professional_counter;
    }

    @Step("Get all profession salary avg")
    public double get_all_salary_avg_of_profession(String profession) {
        double sum_selery = 0;
        while(btn_next.getAttribute("tabindex").equals("0")) {
            sum_selery += get_salary_avg_of_profession(profession);
            next_click();
        }
        return sum_selery;
    }

    @Step("Click to go to the next page")
    public void next_click() {
            btn_next.click();
    }

    @Step("Click to go to the next page")
    public void previous_click() {
        btn_previous.click();
    }

    @Step("Convert string to double")
    public double convert_string_to_double(String price) {
        price = price.replace(',', '.');
        return Double.parseDouble(price.substring(1));
    }

    @Step("Return back to first web table page")
    public void init_table() {
        while(btn_previous.getAttribute("tabindex").equals("0"))
            previous_click();
        previous_click();
    }

}
