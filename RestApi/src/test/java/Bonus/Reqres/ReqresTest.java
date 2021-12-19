package Bonus.Reqres;

import org.junit.Test;

public class ReqresTest extends CommonOps {

    @Test()
    public void test01_POST_http_request() {
        params.put("first_name", "Sara");
        params.put("last_name", "Noiman");
        HomeWorkFlows.add_user();
        Validation.verifyValues(http_request_status_code, 201);
    }

    @Test()
    public void test02_GET_http_request() {
        user_id = "9";
        HomeWorkFlows.get_user_by_ID();
        Validation.verifyValues(http_request_status_code, 200);
    }

    @Test()
    public void test02_DELETE_http_request() {
        HomeWorkFlows.delete_user_by_ID(user_id);
        Validation.verifyValues(http_request_status_code, 204);
    }

    @Test()
    public void test04_PUT_http_request() {
        HomeWorkFlows.update_user();
        Validation.verifyValues(http_request_status_code, 200);
    }

}