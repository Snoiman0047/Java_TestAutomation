package AutomatingElectronApps;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AutomatingElectronApps {
    private WebDriver driver;
    private ChromeOptions opt;
    private DesiredCapabilities capabilities;
    private String path = "C:/Users/snoim/OneDrive/Desktop/Automation/";

    @BeforeClass
    public void startSession() {
        System.setProperty("webdriver.chrome.driver", path + "electrondriver-v3.1.2-win32-x64/electrondriver.exe");
        opt = new ChromeOptions();
        opt.setBinary(path + "Electron API Demos-win32-ia32/Electron API Demos.exe");
        capabilities = new DesiredCapabilities();
        opt.merge(capabilities);
        driver = new ChromeDriver(opt);
    }

    @Test()
    public void test01() {
        driver.findElement(By.id("button-windows")).click();
        driver.findElement(By.id("button-crash-hang")).click();
        driver.findElement(By.id("button-menus")).click();
    }

    @AfterClass
    public void endSession() {
        driver.quit();
    }
}
