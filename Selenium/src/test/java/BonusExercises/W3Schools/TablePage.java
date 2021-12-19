package BonusExercises.W3Schools;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.python.antlr.ast.Str;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Listeners(EventListeners.class)
public class TablePage {

    @FindBy(xpath = "//table[@id='customers']/tbody/tr")
    private List<WebElement> web_table;

    @Step
    public String getTableDescription() {
        return "Table { rows:" + getNumberOfRowsInTable() + ", columns:" + getNumberOfColumnInTable() + " };";
    }

    @Step
    public int getNumberOfRowsInTable() {
        return web_table.size();
    }

    @Step
    public int getNumberOfColumnInTable() {
        return web_table.get(0).findElements(By.tagName("th")).size();
    }

    @Step
    public List<String> getCountriesTableInEuropeList() {
        List<String> europeCountries = getDataListFromCSV("C:/Users/snoim/OneDrive/Desktop/Automation/QA Automation/TestAutomation/Selenium/src/test/java/BonusExercises/W3Schools/countries.txt");
        List<String> countries = getCountriesFromTable();
        List<String> countriesTableInEurope = new ArrayList<>();
        countries.forEach((elem) -> {
            if (europeCountries.contains(elem))
                countriesTableInEurope.add(elem);
        });
        return countriesTableInEurope;
    }

    @Step
    private List<String> getDataListFromCSV(String filePath) {
        List<List<String>> csvList = CSV.readCSV(filePath);
        List<String> list = new ArrayList<>();
        for (List<String> _csvList : csvList) {
            for (String value : _csvList) {
                list.add(value);
            }
        }
        return list;
    }

    @Step
    private List<String> getCountriesFromTable() {
        List<String> countries = new ArrayList<>();
        for (int i = 1; i < web_table.size(); i++)
            countries.add(web_table.get(i).findElements(By.tagName("td")).get(2).getText());
        return countries;
    }

    @Step
    public void writeTableToCSV(String filePath) throws IOException {
        CSV.writeCSV(filePath, convertTableToList());
    }

    @Step
    public List<List<String>> convertTableToList() {
        List<List<String>> list = new ArrayList<>();
        List<String> row = new ArrayList<>();

        for (WebElement elem : web_table.get(0).findElements(By.tagName("th"))) {
            row.add(elem.getText());
        }
        list.add(row);

        for (int i = 1; i < web_table.size(); i++) {
            row = new ArrayList<>();
            for (WebElement elem : web_table.get(i).findElements(By.tagName("td"))) {
                row.add(elem.getText());
            }
            list.add(row);
        }
        return list;
    }

}
