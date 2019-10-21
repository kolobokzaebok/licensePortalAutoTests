package testObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.GeneratePage;
import pageObjects.LoginPage;
import resources.DriverInit;
import resources.Utilz;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateTest {
    private WebDriver driver;
    private GeneratePage gp;

    public GenerateTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        gp = new GeneratePage(this.driver);
    }

    private String getGeneratedKeys() {
        WebDriverWait exp = new WebDriverWait(driver, 3);
        exp.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindowsSet = driver.getWindowHandles();
        List allWindowsList = new ArrayList<>(allWindowsSet);
        driver.switchTo().window(allWindowsList.get(1).toString());
        return driver.findElement(By.tagName("pre")).getText();
    }

    private List separateKeys(String dirty) {
        List<String> clean = new ArrayList<>();
        Matcher m = Pattern.compile("(?<=:\\s)[A-Z0-9-]{19}(?=\\s)")
                .matcher(dirty);
        while (m.find()) {
            clean.add(m.group());
        }
        return clean;
    }

    @BeforeTest
    public void authorize() throws IOException {
        LoginPage lp = new LoginPage(this.driver);
        LoginTest.privateLogin(this.driver, lp.submitEmail(), lp.submitPassword(),
                lp.submitSubmit(), lp.getEmail(), lp.getPassword(), GeneratePage.getAddress(),
                "License Generation");
    }

    @Test
    public void generatePermanentPurchased() throws IOException {
        gp.inputProjectName().clear();
        gp.inputProjectName().sendKeys("autotest_Project1");
        gp.selectCompany().selectByIndex(2);
        gp.selectOrderType().selectByIndex(2);
        gp.inputOrderId().clear();
        gp.inputOrderId().sendKeys("autotest_Order1");
        gp.selectAuthorizedBy().selectByIndex(3);
        gp.inputRequestor().clear();
        gp.inputRequestor().sendKeys("autotest_Requestor1");
        gp.selectBrand().selectByIndex(3);
        gp.selectType().selectByIndex(1);
        gp.selectPermanentTime().selectByIndex(1);
        gp.inputNumberofPackages().clear();
        gp.inputNumberofPackages().sendKeys("5");
        gp.inputNumberofChannels().clear();
        gp.inputNumberofChannels().sendKeys("1");
        gp.clickGenerate().click();

        WebElement confirmationModal = driver.findElement(By.className("modal-open"));
        WebElement confirmButton = confirmationModal.findElement(By.xpath("//button[@id='confirm-button']"));
        WebDriverWait clickConfirm = new WebDriverWait(driver, 3);
        clickConfirm.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();

        Utilz.writeToFileCsv(separateKeys(getGeneratedKeys()), "permanentPurchased.csv");
    }

    @AfterTest()
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }


}
