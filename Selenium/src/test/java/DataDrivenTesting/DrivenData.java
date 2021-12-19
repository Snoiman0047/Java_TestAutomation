package DataDrivenTesting;

import org.testng.annotations.DataProvider;

public class DrivenData {

    @DataProvider(name = "wikipedia-data-provider")
    public Object[][] getDataObject()
    {
        return new Object[][] {
                {"Israel", "Israel"},
                {"Automation", "Automation"},
                {"BlahBlah", "Search results"}
        };
    }
}
