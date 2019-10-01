package testObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import resources.Base;
import resources.DriverInit;

import java.io.IOException;

public class LoginTest{
    private WebDriver driver;
    LoginPage lp;

    public LoginTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        lp = new LoginPage(driver);
    }

    static void privateLogin(WebDriver driver,
                             WebElement loginField,
                             WebElement passwordField,
                             WebElement clickButton,
                             String login,
                             String password,
                             String url,
                             String title) {
        driver.get(url);
        loginField.clear();
        loginField.sendKeys(login);
        passwordField.clear();
        passwordField.sendKeys(password);
        clickButton.click();
        Assert.assertEquals(driver.getTitle(), title);
    }

    @Test
    public void unsuccessfulLogin() {
        privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), "Zalupa", "Zalupa", lp.getUrl(),
                "Login");
    }

    @Test
    public void successfulLogin() {
        privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), lp.getUrl(),
                "License Management");
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
