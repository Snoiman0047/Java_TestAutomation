package BonusExercises.Grafana;

import org.testng.annotations.Test;

public class Grafana_API_test extends Common_ops{

    @Test
    public void Get_request() {
        Grafana_work_flow.get_user_by_login_or_email(user_name, 200);
    }

    @Test
    public void PUT_request() {
        Grafana_work_flow.change_password("new_admin", 200);
    }

    @Test
    public void POST_request() {
        Grafana_work_flow.revoke_auth_token_of_user(364, 200);
    }

}
