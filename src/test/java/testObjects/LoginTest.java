package testObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

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
        Assert.assertEquals(driver.getTitle(), "License Management");
    }

    //@Test
    public void successfulLogin() {
        driver.get(super.baseUrl);
        submitEmail().clear();
        submitEmail().sendKeys(super.baseEmail);
        submitPassword().clear();
        submitPassword().sendKeys(super.basePassword);
        submitSubmit().click();
        Assert.assertEquals(driver.getTitle(), "License Management");
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
        driver = null;
    }

}
