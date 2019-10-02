package testObjects;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.DisablePage;
import pageObjects.LoginPage;
import resources.DriverInit;

import java.io.IOException;

public class DisableTest {
    private WebDriver driver;
    DisablePage dp;

    public DisableTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        this.dp = new DisablePage(this.driver);
    }

    @BeforeTest
    public void authorize() throws IOException {
        LoginPage lp = new LoginPage(this.driver);
        LoginTest.privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), DisablePage.getAddress(),
                "Disable License");
    }


    @Test
    public void disableKey() {
        dp.inputKey().clear();
        dp.inputKey().sendKeys("ZL91-BEU5-18LK-I9BE");
        dp.clickDisable().click();
    }


    @AfterTest(enabled = false)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }

}
