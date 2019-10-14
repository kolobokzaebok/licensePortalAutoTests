package testObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.ActivatePage;
import pageObjects.LoginPage;
import resources.DriverInit;

import java.io.File;
import java.io.IOException;

public class ActivateTest {
    private WebDriver driver;
    private ActivatePage ap;
    private String downloadDir;

    public ActivateTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        this.ap = new ActivatePage(this.driver);
        this.downloadDir = dr.getDwnloadDirPath();
    }

    @BeforeTest
    public void authorize() throws IOException {
        LoginPage lp = new LoginPage(this.driver);
        LoginTest.privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), ActivatePage.getAddress(),
                "License Activation");
    }

    @Test
    public void activateKey() throws InterruptedException {
        String keyToActivate = "D877-VP1W-42VJ-34MU";
        ap.inputKey().clear();
        ap.inputKey().sendKeys(keyToActivate);
        ap.inputHwid().clear();
        ap.inputHwid().sendKeys("05a21913cbabbe8027383c8ba6db645ac3");
        ap.clickSubmit().click();
        Thread.sleep(2000);
        File f = new File(this.downloadDir + keyToActivate + "-activation.txt");
        Assert.assertTrue(f.exists());
//        f.delete();
    }

    @AfterTest()
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }


}
