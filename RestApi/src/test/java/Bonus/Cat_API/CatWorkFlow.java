package Bonus.Cat_API;

import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Step;
import okhttp3.Response;
import org.json.simple.JSONObject;

import java.io.IOException;

public class CatWorkFlow extends CommonOps{

    @Step
    public static void search_by_category(String categories) {
        API_actions.search_category_with_category_filter(categories);
        print_response("SEARCH BY CATEGORIES request result");
    }

    @Step
    public static void search_and_pagination(int images_limit, int from_page, String order) {
        API_actions.search_images_from_page_in_order(images_limit, from_page, order);
        print_response("SEARCH AND PAGINATION request result");
    }

    @Step
    public static void search_by_breed(String breed) {
        API_actions.search_by_breed_filter(breed);
        print_response("SEARCH BY BREED request result");
    }

    @Step
    public static void search_by_file_type(String breed) {
        API_actions.search_by_file_type_filter(breed);
        print_response("SEARCH BY FILE TYPE request result");
    }

    @Step
    public static void print_response(String header) {
        System.out.println("\n--  " + header + "  --");
        response.prettyPrint();
        System.out.println("\n");
    }

    @Step
    public static void verify_response_status_code(int status_code) {
        Validation.verifyValues(http_request_status_code, status_code);
    }

    @Step
    public static void create_vote(String image_id, String sub_id, int value) {
        API_actions.create_a_vote(image_id, sub_id, value);
        print_response("CREATE VOTE request result");
    }

    @Step
    public static void delete_vote(int vote_id) {
        API_actions.delete_vote(vote_id);
        print_response("DELETE VOTE request result");
    }

    @Step
    public static void save_img_as_favorite(String image_id, String sub_id) throws IOException {
        API_actions.save_ing_as_favorite(image_id, sub_id);
        print_response("SAVA IMAGE AS FAVORITE request result");
    }

    @Step
    public static void delete_favorite_img(int img_id) {
        API_actions.delete_favourite(img_id);
        print_response("DELETE FAVORITES request result");
    }

    @Step
    public static void upload_img(String file_path, String img_name, String sub_id) throws IOException {
        API_actions.upload_img(file_path, img_name, sub_id);
        System.out.println("UPLOAD IMAGE request result");
    }

    @Step
    public static void delete_uploaded_image(int img_id) {
        API_actions.delete_uploaded_image(img_id);
        print_response("DELETE UPLOADED IMAGE request result");
    }



}
