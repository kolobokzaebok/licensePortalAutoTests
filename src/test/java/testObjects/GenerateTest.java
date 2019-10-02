package testObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.GeneratePage;
import pageObjects.LoginPage;
import resources.DriverInit;

import java.io.IOException;

public class GenerateTest {
    private WebDriver driver;
    private GeneratePage gp;

    public GenerateTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        gp = new GeneratePage(this.driver);
    }

    @BeforeTest
    public void authorize() throws IOException {
        LoginPage lp = new LoginPage(this.driver);
        LoginTest.privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), GeneratePage.getAddress(),
                "License Generation");
    }

    @Test
    public void generatePermanentPurchased() {
        Select selectCompany = new Select(gp.selectCompany());
        Select selectOrderType = new Select(gp.selectOrderType());
        Select selectAuthorizedBy = new Select(gp.selectAuthorizedBy());
        Select selectBrand = new Select(gp.selectBrand());
        Select selectLicenseType = new Select(gp.selectType());
        Select selectPermanentTime = new Select(gp.selectPermanentTime());

        gp.inputProjectName().clear();
        gp.inputProjectName().sendKeys("autotest_Project1");
        selectCompany.selectByIndex(3);
        selectOrderType.selectByIndex(2);
        gp.inputOrderId().clear();
        gp.inputOrderId().sendKeys("autotest_Order1");
        selectAuthorizedBy.selectByIndex(3);
        gp.inputRequestor().clear();
        gp.inputRequestor().sendKeys("autotest_Requestor1");
        selectBrand.selectByIndex(3);
        selectLicenseType.selectByIndex(1);
        selectPermanentTime.selectByIndex(1);
        gp.inputNumberofPackages().clear();
        gp.inputNumberofPackages().sendKeys("10");
        gp.inputNumberofChannels().clear();
        gp.inputNumberofChannels().sendKeys("1");
        gp.clickGenerate().click();
        WebElement huj = driver.findElement(By.className("modal-open"));
        huj.findElement(By.xpath("//button[@id='confirm-button']")).click();
    }

    @AfterTest(enabled = false)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }


}
