package testObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.DeactivatePage;
import pageObjects.LoginPage;
import resources.DriverInit;
import resources.Utilz;

import java.io.IOException;

public class DeactivateTest {

    private WebDriver driver;
    private DeactivatePage dp;
    private String csvKeysToDeactivate;

    public DeactivateTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        this.dp = new DeactivatePage(this.driver);
        this.csvKeysToDeactivate = dr.getCsvPermPurchPath();
    }

    @BeforeTest
    public void authorize() throws IOException {
        LoginPage lp = new LoginPage(this.driver);
        LoginTest.privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), DeactivatePage.getAddress(),
                "License Deactivation");
    }

    @Test
    public void deactivateKey() throws IOException, InterruptedException {
        dp.selectReason().selectByIndex(1);
        int count = 1;
        for (Object key: getKeysToDeactivate()) {
            WebElement keyToInput = driver.findElement(By.id("license-key-" + count));
            Utilz.setExplicitWait(driver, 2, keyToInput);
            keyToInput.clear();
            keyToInput.sendKeys(key.toString());
            if (count < getKeysToDeactivate().length) {
                dp.clickPlus().click();
            }
            count += 1;
        }
        dp.inputNewHwid().clear();
        dp.inputIntegrator().clear();
        dp.inputIntegrator().sendKeys("integrator_test");
        dp.inputEndUser().clear();
        dp.inputEndUser().sendKeys("endUser_test");
        dp.clickDeactivateNow().click();
        Thread.sleep(1000);
        Assert.assertTrue(dp.checkLegend().getText().contains("Completed"));
    }

    @AfterTest()
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }

    private Object[] getKeysToDeactivate() throws IOException {
        return Utilz.getKeys(csvKeysToDeactivate);
    }

}
