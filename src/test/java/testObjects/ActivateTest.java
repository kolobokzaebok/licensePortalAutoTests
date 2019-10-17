package testObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.ActivatePage;
import pageObjects.LoginPage;
import resources.DriverInit;
import resources.Utilz;

import java.io.File;
import java.io.IOException;

public class ActivateTest {
    private WebDriver driver;
    private ActivatePage ap;
    private String downloadDir;
    private String csvKeysToActivate;
    private String hwid = "05a21913cbabbe8027383c8ba6db645ac3";

    public ActivateTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        this.ap = new ActivatePage(this.driver);
        this.downloadDir = dr.getDwnloadDirPath();
        this.csvKeysToActivate = dr.getCsvPermPurchPath();
    }

    @BeforeTest
    public void authorize() throws IOException {
        LoginPage lp = new LoginPage(this.driver);
        LoginTest.privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), ActivatePage.getAddress(),
                "License Activation");
    }

    @Test(dataProvider = "getKeys")
    public void activateKey(String key) throws InterruptedException {
        ap.inputKey().clear();
        ap.inputKey().sendKeys(key);
        ap.inputHwid().clear();
        ap.inputHwid().sendKeys(this.hwid);
        ap.clickSubmit().click();
        Thread.sleep(2000);
        File f = new File(this.downloadDir + key + "-activation.txt");
        Assert.assertTrue(f.exists());
        f.delete();
    }

    @AfterTest()
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }

    @DataProvider(name = "getKeys")
    private Object[] getKeysToActivate() throws IOException {
        return Utilz.getKeys(csvKeysToActivate);
    }




}
