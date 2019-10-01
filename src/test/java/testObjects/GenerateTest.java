package testObjects;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.GeneratePage;
import pageObjects.LoginPage;
import resources.DriverInit;

import java.io.IOException;

public class GenerateTest {
    private WebDriver driver;
    private LoginPage lp;

    public GenerateTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        GeneratePage gp = new GeneratePage(this.driver);
        lp = new LoginPage(this.driver);
    }

    @BeforeTest
    public void authorize() {
        LoginTest.privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), GeneratePage.getAddress(),
                "License Generation");
    }

    @Test
    public void generatePermanentPurchased() {
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }


}
