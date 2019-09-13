package licenseportal;

import org.testng.annotations.Test;
import pageObjects.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginTest extends LoginPage {
    private String loginURL = "";

    public LoginTest() throws IOException {
    }

    @Test
    public void unsuccessfulLogin() {
        driver.get(loginURL);
        submitEmail().clear();
        submitEmail().sendKeys("Zalupa");
        submitPassword().clear();
        submitPassword().sendKeys("Zalupa");
        submitSubmit().click();
        driver.close();
    }

    @Test
    public void successfulLogin() {
        driver.get(loginURL);
        submitEmail().clear();
        submitEmail().sendKeys("Zalupa");
        submitPassword().clear();
        submitPassword().sendKeys("Zalupa");
        submitSubmit().click();
        driver.close();
    }

}
