package Bonus.Dog_API;

import io.qameta.allure.Description;
import org.junit.Test;


public class Dog_test extends CommonOps {

    @Test()
    @Description("Get all breeds")
    public void test01_list_all_breeds() {
        DogWorkFlow.get_all_breeds();
        DogWorkFlow.verify_response_status_code(200);
    }

    @Test()
    @Description("Random dog img")
    public void test02_random_image() {
        DogWorkFlow.random_img(3);
        DogWorkFlow.verify_response_status_code(200);
    }

    @Test()
    @Description("Get dogs by breeds with random option")
    public void test03_get_by_breeds_and_random() {
        http_request_url = "https://dog.ceo/api/breed";
        DogWorkFlow.get_all_by_breed("hound", true);
        DogWorkFlow.verify_response_status_code(200);
    }

    @Test()
    @Description("Get SUB breeds list")
    public void test04_sub_breeds_list() {
        DogWorkFlow.sub_breed_list("hound");
        DogWorkFlow.verify_response_status_code(200);
    }

    @Test()
    @Description("Get sub breeds images list with random option")
    public void test05_sub_breeds_img() {
        DogWorkFlow.sub_breed_img("hound", "afghan", -1);
        DogWorkFlow.verify_response_status_code(200);
 //
        /*  BROWSE BREEDS LIST  */
        /*  DogWorkFlow.sub_breed_img("hound", null, 0);  */
    }

}
