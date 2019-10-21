package testObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.InformationPage;
import pageObjects.LoginPage;
import resources.DriverInit;
import resources.Utilz;

import java.io.IOException;

public class InformationTest {

    private WebDriver driver;
    private InformationPage ip;

    public InformationTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        this.ip = new InformationPage(this.driver);
    }

    private static void getInfo(WebDriver driver,
                                WebElement inputField,
                                WebElement clickButton,
                                WebElement result,
                                String keyOrHwid,
                                String expectedText) {
        inputField.clear();
        inputField.sendKeys(keyOrHwid);
        clickButton.click();
        Utilz.setExplicitWait(driver, 5, result);
        Assert.assertTrue(result.getText().contains(expectedText));
    }

    @BeforeTest
    public void authorize() throws IOException {
        LoginPage lp = new LoginPage(this.driver);
        LoginTest.privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), InformationPage.getAddress(),
                "License Information");
    }


    @Test
    public void getInformationOnProperKey() {
        getInfo(this.driver, ip.inputKey(), ip.clickGetInfo(),
                ip.checkProperKey(), "U1HO-373Q-KGX3-1PR3","Current");
    }

    @Test
    public void getInformationOnEmpty() {
        driver.navigate().refresh();
        getInfo(this.driver, ip.inputKey(), ip.clickGetInfo(),
                ip.checkProperKey(), "","Error");
    }

    @Test
    public void getInformationOnProperHwid() {
        driver.navigate().refresh();
        getInfo(this.driver, ip.inputKey(), ip.clickGetInfo(),
                ip.checkProperKey(), "05a21913cbabbe8027383c8ba6db645ac3","Key");
    }

    @AfterTest()
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }





}
