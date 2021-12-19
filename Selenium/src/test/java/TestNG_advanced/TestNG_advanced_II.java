package TestNG_advanced;

import org.testng.annotations.Test;

public class TestNG_advanced_II {

    @Test (groups = {"sanity"})
    public void MyTestNG2_Test01() {
        System.out.println("TestNG_advanced_II_test01");
    }

    @Test (groups = {"sanity"})
    public void MyTestNG2_Test02() {
        System.out.println("TestNG_advanced_II_test02");
    }

    @Test (groups = {"regression"})
    public void MyTestNG2_Test03() {
        System.out.println("TestNG_advanced_II_test03");
    }

}
