package BonusExercises.Grafana;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners(Event_listeners.class)
public class Grafana_test extends Common_ops {

    @Test(priority = 0, testName = "Login to site")
    @Description("Verification of login the site")
    public void test_login() {
        Grafana_work_flow.log_in();
        Grafana_work_flow.verify_log_in();
    }

    @Test(priority = 1, testName = "Navigate to personal area")
    @Description("Verification of reacting the personal area in site")
    public void test_navigate_to_personal_area() {
        Grafana_work_flow.navigate_to_personal_area();
        Grafana_work_flow.verify_reacting_the_personal_area("http://localhost:3000/?orgId=1");
    }

    @Test(priority = 2, testName = "Navigate to personal area")
    @Description("Verification of reacting the personal area in site")
    public void test_navigate_to_plugin() {
        Grafana_work_flow.plugin_test();
    }

    @Test(priority = 3)
    public void test(){
        Grafana_work_flow.users_test();
    }

    @Test(priority = 4, testName = "Log out the site")
    @Description("Verification of logout the site")
    public void test_logout() {
        _private_area.changeToLightBackgroundColor();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String backgroungColor = _private_area.getHexColor(driver);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        Assert.assertEquals(backgroungColor, "#ffffff");
        Grafana_work_flow.log_out();
        Grafana_work_flow.verify_log_out();
    }

}
