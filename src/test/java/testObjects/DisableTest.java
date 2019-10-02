package testObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.DisablePage;
import pageObjects.GeneratePage;
import pageObjects.LoginPage;
import resources.DriverInit;

import java.io.IOException;

public class DisableTest {
    private WebDriver driver;

    public DisableTest(WebDriver driver) throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
    }

    @BeforeTest
    public void authorize() throws IOException {
        LoginPage lp = new LoginPage(this.driver);
        LoginTest.privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), GeneratePage.getAddress(),
                "License Generation");
    }


    @Test
    public void disableKey() {
        DisablePage dp = new DisablePage(this.driver);
        dp.inputKey().clear();
        dp.inputKey().sendKeys("ZL91-BEU5-18LK-I9BE");
        dp.clickDisable().click();
//        Assert.assertEquals(dp.getLicenseInfo(), "1 key(s) disabled");
    }


    @AfterTest(enabled = false)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }








}
