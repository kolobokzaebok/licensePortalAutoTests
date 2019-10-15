package testObjects;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.DisablePage;
import pageObjects.LoginPage;
import resources.DriverInit;
import resources.Utilz;

import java.io.IOException;

public class DisableTest {
    private WebDriver driver;
    private DisablePage dp;
    private String csvPermPurch;

    public DisableTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        this.dp = new DisablePage(this.driver);
        this.csvPermPurch = dr.getCsvPermPurchPath();
    }

    @BeforeTest
    public void authorize() throws IOException {
        LoginPage lp = new LoginPage(this.driver);
        LoginTest.privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), DisablePage.getAddress(),
                "Disable License");
    }

    @Test(dataProvider = "getKeys")
    public void disableKey(String key) {
        dp.inputKey().clear();
        dp.inputKey().sendKeys(key);
        dp.clickDisable().click();
    }

    @AfterTest()
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }

    @DataProvider(name = "getKeys")
    private Object[] getKeysToDisable() throws IOException {
        return Utilz.getKeys(csvPermPurch);
    }




}
