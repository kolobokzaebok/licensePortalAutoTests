package testObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ReportPage;
import resources.DriverInit;
import resources.Utilz;

import java.io.IOException;

public class ReportTest {

    private WebDriver driver;
    private ReportPage rp;

    public ReportTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        this.rp = new ReportPage(this.driver);
    }

    @BeforeTest
    public void authorize() throws IOException {
        LoginPage lp = new LoginPage(this.driver);
        LoginTest.privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), ReportPage.getAddress(),
                "Activation Report");
    }


    @Test
    public void generateReport() {
        rp.selectCompany().selectByIndex(2);
        rp.useCalendar().click();
        rp.chooseRange().click();
        rp.clickGenerateRange().click();
        Utilz.setExplicitWait(this.driver, 3, rp.checkHeader());
        Assert.assertTrue(rp.checkHeader().getText().contains("Key"));
    }

    @AfterTest()
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }

}
