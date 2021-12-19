package Automating_Desktop_Apps;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.net.URL;
import io.appium.java_client.windows.WindowsDriver;

public class Calculator_desktop_app {
    private WindowsDriver driver;
    private DesiredCapabilities capabilities;
    private final String calcApp = "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App";
    private Calculator_class calculator;

    @BeforeClass
    public void setup() throws IOException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", calcApp);
        driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        calculator = PageFactory.initElements(driver, Calculator_class.class);
    }

    @BeforeMethod
    public void clear_calculator() {
        calculator.clear();
    }

    @Test
    public void Addition() {
        calculator.click_four();
        calculator.plus_action();
        calculator.click_two();
        calculator.equals_and_verify(6, "addition");
    }

    @Test
    public void Combination() {
        calculator.click_one();
        calculator.click_zero();
        calculator.multiply_action();
        calculator.click_three();
        calculator.plus_action();
        calculator.click_five();
        calculator.equal_action();
        calculator.divide_action();
        calculator.click_seven();
        calculator.equals_and_verify(5, "combination");
    }

    @Test
    public void Division() {
        calculator.click_two();
        calculator.click_two();
        calculator.divide_action();
        calculator.click_one();
        calculator.click_one();
        calculator.equals_and_verify(2, "division");
    }

    @Test
    public void Multiplication() {
        calculator.click_nine();
        calculator.multiply_action();
        calculator.click_five();
        calculator.equals_and_verify(45, "multiplication");
    }

    @Test
    public void Subtraction() {
        calculator.click_eight();
        calculator.minus_action();
        calculator.click_six();
        calculator.equals_and_verify(2, "subtraction");
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
