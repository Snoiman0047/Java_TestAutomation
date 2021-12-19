package Bonus.Cat_API;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.annotations.Test;

import java.io.IOException;


public class Cat_test extends CommonOps{

    @Test(priority = 0)
    public void test01_search_and_pagination() {
        status_code = 200;
        CatWorkFlow.search_and_pagination(5, 10, "desc");
        CatWorkFlow.verify_response_status_code(status_code);
    }

    @Test(priority = 1)
    public void test02_search_by_breed(){
        CatWorkFlow.search_by_breed("beng");
        CatWorkFlow.verify_response_status_code(status_code);
    }

    @Test(priority = 2)
    public void test03_search_by_categories(){
        CatWorkFlow.search_by_category("1,4");
        CatWorkFlow.verify_response_status_code(status_code);
    }

    @Test(priority = 3)
    public void test04_search_by_file_type(){
        CatWorkFlow.search_by_file_type("gif");
        CatWorkFlow.verify_response_status_code(status_code);
    }

    @Test(priority = 4)
    public void test05_create_vote() {
        CatWorkFlow.create_vote("asf2", "user", 1);
        CatWorkFlow.verify_response_status_code(status_code);

    }

    @Test(priority = 5)
    public void test06_delete_votes() {
        CatWorkFlow.delete_vote(integer);
        CatWorkFlow.verify_response_status_code(status_code);
    }

    @Test(priority = 6)
    public void test07_sava_img_as_favorite() throws IOException {
        CatWorkFlow.save_img_as_favorite("9ccXTANkb", "user");
    }

    @Test(priority = 7)
    public void test8_delete_favorite() {
        CatWorkFlow.delete_favorite_img(integer);
    }

    @Test(priority = 8)
    public void test9_upload_file() throws IOException {
        status_code = 201;
        CatWorkFlow.upload_img("C:/Users/snoim/Downloads/cat.png", "cat", "user");
        CatWorkFlow.verify_response_status_code(status_code);
    }

    @Test(priority = 9)
    public void test10_delete_uploaded_file() {
        status_code = 200;
        CatWorkFlow.delete_uploaded_image(integer);
        CatWorkFlow.verify_response_status_code(status_code);
    }
}
