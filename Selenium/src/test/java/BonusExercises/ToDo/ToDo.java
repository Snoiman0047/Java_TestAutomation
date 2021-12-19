package BonusExercises.ToDo;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import static org.testng.Assert.*;

@Listeners(TodoListener.class)
public class ToDo {

    WebDriver driver;
    int todos = 0;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://todomvc.com/examples/react/#/");
    }

    @Test
    public void test01_create() {
        createAll("Wake up", "Smile to world", "BlahBlah");
        verifyTodos();
    }

    @Test
    public void test02_Delete() {
        deleteAll("BlahBlah");
        verifyTodos();
    }

    @Test
    public void test03_change() {
        change(2, "Smile to world:)");
        verifyTodos("Smile to world:)");
    }

    @Test
    public void test04_completed() {
        complete("Wake up");
        verifyCompleted("Wake up");
    }

    @Test
    public void test05_filter() throws Exception {
        verifyFilters(filterAll(), filterActive(), filterCompleted());
    }

    @Test
    public void test06_deleteCompleted() {
        deleteCompletedTodos();
        verifyNoCompleted();
    }

    //region Steps

    @Step
    public int countTodos() {
        return driver.findElements(By.xpath("//ul[@class='todo-list']/li")).size();
    }

    @Step
    public int getTodoIndex(String todoDescription) {
        int index = 1;
        while(index <= countTodos()) {
            String path = "//ul[@class='todo-list']/li[" + index + "]/div/label";
            if (compare(driver.findElement(By.xpath(path)).getText(), todoDescription))
                return index;
            index++;
        }
        return 0;
    }

    @Step
    private boolean compare(String elem, String todoDesc) {
        return elem.compareTo(todoDesc) == 0;
    }

    @Step
    public void createAll(String ...todoDescription) {
        WebElement todosElem =  driver.findElement(By.xpath("//input[@class='new-todo']"));
        for(String todo: todoDescription)
            createIt(todosElem, todo);
    }

    @Step
    private void createIt(WebElement todosElem, String todoDescription) {
        todosElem.sendKeys(todoDescription, Keys.ENTER);
        todos++;
    }

    @Step
    public void deleteAll(String ...todoDescription) {
        for(String todo: todoDescription) {
            deleteIt(getTodoIndex(todo)); }
    }

    @Step
    private void deleteIt(int todoIndex) {
        Actions actions = new Actions(driver);
        String url = "//ul[@class='todo-list']/li[" + todoIndex + "]";
        actions.moveToElement(driver.findElement(By.xpath(url)));
        actions.moveToElement(driver.findElement(By.xpath(url + "/div/button[@class='destroy']"))).click().build().perform();
        todos--;
    }

    @Step
    public void verifyTodos() {
        assertEquals(countTodos(), todos, "Expected amount");
    }

    @Step
    public void verifyTodos(String todoDescription) {
        assertTrue(getTodoIndex(todoDescription) > 0);
    }

    @Step
    public void change(int indexToChange, String changeValue) {
        Actions actions = new Actions(driver);
        String url = "//ul[@class='todo-list']/li[" + indexToChange + "]";
        actions.moveToElement(driver.findElement(By.xpath(url))).doubleClick();
        actions.moveToElement(driver.findElement(By.xpath(url + "/input[@class='edit']")));
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        actions.moveToElement(driver.findElement(By.xpath(url + "/input[@class='edit']"))).sendKeys(changeValue, Keys.ENTER).build().perform();
    }

    @Step
    public void complete(String todoDescription) {
        String url = "//ul[@class='todo-list']/li[" + getTodoIndex(todoDescription) + "]";
        driver.findElement(By.xpath(url + "/div/input")).click();
    }

    @Step
    public void verifyCompleted(String todoDescription) {
        int index = getTodoIndex(todoDescription);
        String path = "//ul[@class='todo-list']/li[" + index + "]";
        assertTrue(index > 0 && compare(driver.findElement(By.xpath(path)).getAttribute("class"), "completed"));
    }

    @Step
    public boolean filterAll() throws Exception {
        clickAll();
        if (countTodos() != todos)
            throw new Exception("All filter did not work as expected");
        return countTodos() == todos;
    }

    @Step
    public void clickAll() {
        driver.findElement(By.xpath("//a[@href='#/']")).click();
    }

    @Step
    public boolean filterActive() throws Exception {
        clickAll();
        int completed = completedCount();
        driver.findElement(By.xpath("//a[@href='#/active']")).click();
        if ((todos - completed) != countTodos())
            throw new Exception("Active filter did not work as expected");
        return ((todos - completed) != countTodos());
    }

    @Step
    public boolean filterCompleted() throws Exception {
        clickAll();
        int completed = completedCount();
        driver.findElement(By.xpath("//a[@href='#/completed']")).click();
        if (completed != countTodos())
            throw new Exception("Completed filter did not work as expected");
        return (completed != countTodos());
    }

    @Step
    private int completedCount() {
        int index = 1, count = 0;
        while(index <= countTodos()) {
            String path = "//ul[@class='todo-list']/li[" + index + "]";
            if (compare(driver.findElement(By.xpath(path)).getAttribute("class"), "completed"))
                count++;
            index++;
        }
        return count;
    }

    @Step
    public void verifyFilters(boolean all, boolean active, boolean completed) {
        assertTrue(all == active == completed, "Some filter did not work as expected");
    }

    @Step
    public void deleteCompletedTodos() {
       driver.findElement(By.className("clear-completed")).click();
    }

    @Step
    public void verifyNoCompleted() {
        boolean flag = false;
        int index = 1;
        while(index <= countTodos()) {
            String path = "//ul[@class='todo-list']/li[" + index + "]";
            if (compare(driver.findElement(By.xpath(path)).getAttribute("class"), "completed")) {
                flag = true;
                break;
            }
            index++;
        }
        assertFalse(flag);
    }

    //endregion

    @AfterClass
    public void closeSession() {
        driver.quit();
    }

}
