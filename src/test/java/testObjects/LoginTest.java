package testObjects;


import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LoginPage;


import java.io.IOException;

public class LoginTest extends LoginPage {


    public LoginTest() throws IOException {
    }

    @Test
    public void unsuccessfulLogin() {
        driver.get(super.baseUrl);
        submitEmail().clear();
        submitEmail().sendKeys("Zalupa");
        submitPassword().clear();
        submitPassword().sendKeys("Zalupa");
        submitSubmit().click();
        Assert.assertEquals(driver.getTitle(), "Login");
    }


    @Test
    public void successfulLogin() {
        driver.get(baseUrl);
        submitEmail().clear();
        submitEmail().sendKeys(super.baseEmail);
        submitPassword().clear();
        submitPassword().sendKeys(super.basePassword);
        submitSubmit().click();
        Assert.assertEquals(driver.getTitle(), "License Management");
    }

    public void successfulLogin(String address) {
        driver.get(address);
        submitEmail().clear();
        submitEmail().sendKeys(super.baseEmail);
        submitPassword().clear();
        submitPassword().sendKeys(super.basePassword);
        submitSubmit().click();
        Assert.assertEquals(driver.getTitle(), "License Management");
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }



}
