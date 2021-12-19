package TestNG_advanced;

import org.testng.annotations.Test;

public class TestNG_advanced_I {

    @Test(groups = {"regression"})
    public void test01() {
        System.out.println("TestNG_advanced_I_test01");
    }

    @Test (groups = {"regression"})
    public void test02() {
        System.out.println("TestNG_advanced_I_test02");
    }

    @Test (groups = {"sanity"})
    public void test03() {
        System.out.println("TestNG_advanced_I_test03");
    }

}
