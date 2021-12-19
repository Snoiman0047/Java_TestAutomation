package Bonus.Cat_API;

import com.mashape.unirest.http.Unirest;
import io.qameta.allure.Step;
import okhttp3.*;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

public class API_actions extends CommonOps{


    @Step
    public static void search_category_with_category_filter(String categories_filter) {
        init_http_response();
        response = http_request.get(http_request_url + "images/search?category_ids=" + categories_filter);
        update_response_status_code(response.getStatusCode());
    }

    @Step
    public static void search_images_from_page_in_order(int images_limit, int from_page, String order) {
        init_http_response();
        response = http_request.get(http_request_url +  "images/search?limit=" + images_limit + "&page=" + from_page + "&order=" + order);
        update_response_status_code(response.getStatusCode());
    }

    @Step
    public static void search_by_breed_filter(String bread) {
        init_http_response();
        response = http_request.get(http_request_url + "images/search?breed_ids=" + bread);
        update_response_status_code(response.getStatusCode());
    }

    @Step
    public static void search_by_file_type_filter(String file_type_filter) {
        init_http_response();
        response = http_request.get(http_request_url + "images/search?mime_types=" + file_type_filter);
        update_response_status_code(response.getStatusCode());
    }

    @Step
    public static void create_a_vote(String image_id, String sub_id, int value) {
        init_http_response();
        params = new JSONObject();
        params.put("image_id", image_id);
        params.put("sub_id", sub_id);
        params.put("value", value);
        http_request.body(params.toJSONString());
        response = http_request.post(http_request_url + "votes");
        update_response_status_code(response.getStatusCode());
        jp = response.jsonPath();
        integer = jp.get("id");
    }

    @Step
    public static void delete_vote(int vote_id) {
        init_http_response();
        response = http_request.delete(http_request_url + "votes/" + vote_id);
        update_response_status_code(response.getStatusCode());
    }

    @Step
    public static void save_ing_as_favorite(String image_id, String sub_id) throws IOException {
        init_http_response();
        params = new JSONObject();
        params.put("image_id", image_id);
        params.put("sub_id", sub_id);
        http_request.body(params.toJSONString());
        response = http_request.post(http_request_url + "favourites");
        update_response_status_code(response.getStatusCode());
        jp = response.jsonPath();
        integer = jp.get("id");
    }

    @Step
    public static void delete_favourite(int img_id) {
        init_http_response();
        response = http_request.delete(http_request_url + "favourites/" + img_id);
        update_response_status_code(response.getStatusCode());
    }

    @Step
    public static void upload_img(String file_path, String img_name, String sub_id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", img_name,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(file_path)))
                .addFormDataPart("sub_id",sub_id)
                .build();
        Request request = new Request.Builder()
                .url(http_request_url + "images/upload")
                .method("POST", body)
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("x-api-key", key)
                .build();
        Response response = client.newCall(request).execute();
        update_response_status_code(response.code());
    }

    @Step
    public static void delete_uploaded_image(int img_id) {
        init_http_response();
        response = http_request.delete(http_request_url + "images/" + img_id);
        update_response_status_code(response.getStatusCode());
    }

    @Step
    private static void update_response_status_code(int status_code) {
        http_request_status_code = status_code;
    }

    @Step
    private static void init_http_response() {
        http_request.header("Content-Type", "application/json");
    }

}
