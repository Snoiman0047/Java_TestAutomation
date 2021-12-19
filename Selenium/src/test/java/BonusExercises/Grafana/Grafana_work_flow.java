package BonusExercises.Grafana;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

@Listeners(Event_listeners.class)
public class Grafana_work_flow extends Common_ops {

    @Step("Login by user name and password")
    public static void log_in() {
        _login.send_user_name(user_name);
        _login.send_password(password);
        _login.login_action();
    }

    @Step("Navigate to personal area")
    public static void navigate_to_personal_area() {
        init_password_page();
        _password.skip();
        init_private_area_page();
        init_configuration_page();
    }

    @Step
    public static void plugin_test() {
        _configuration.goToPlugins();
        sleep(5);
        _configuration.Search("AJAX");
        _configuration.SearchBox.sendKeys(Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.tagName("h2")).isDisplayed(),"The Element Exist!");
    }

    @Step
    public static void users_test() {
        _configuration.verify_users(driver, user_name, user_name + "@localhost");
    }

    @Step("Login by user name and password")
    public static void log_out() {
        _private_area.show_user_actions(driver);
        _private_area.sign_out();
    }

    @Step
    public static void get_user_by_login_or_email(String login_or_email, int expected_status_code) {
        API_actions.GET_request_by_login_or_email(login_or_email);
        print_response("GET request");
//        Validation.verifyValues(http_request_status_code,  expected_status_code);
    }

    @Step
    public static void change_password(String new_password, int expected_status_code) {
        API_actions.PUT_request_change_password(password, new_password);
        print_response("PUT request");
//        Validation.verifyValues(http_request_status_code,  expected_status_code);
    }

    @Step
    public static void revoke_auth_token_of_user(int auth_token_ID, int expected_status_code) {
        API_actions.POST_revoke_auth_token_of_user(auth_token_ID);
        print_response("PUT request");
//        Validation.verifyValues(http_request_status_code,  expected_status_code);
    }

    @Step("Print result")
    public static void print_response(String header) {
        System.out.println("\n--  " + header + "  --");
        response.prettyPrint();
        System.out.println("\n");
    }

    @Step("verify login the site")
    public static void verify_log_in() {
        sleep(1);
        Validation.verifyValues(_login.get_alert_message(driver), "Logged in");
    }

    @Step("verify reacting the personal area in site")
    public static void verify_reacting_the_personal_area(String url) {
        Validation.verifyValues(driver.getCurrentUrl(), url);
    }

    @Step("verify logout the site")
    public static void verify_log_out() {
        Validation.verifyValues(driver.getCurrentUrl(), url);
    }

}
