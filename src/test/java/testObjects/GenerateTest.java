package testObjects;

import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.GeneratePage;
import pageObjects.LoginPage;
import resources.DriverInit;

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
//        System.out.println(driver.findElement(By.tagName("pre")).getText());
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

    private void writeToFile(List<String> myList, String fileName) {
        File file = new File("/home/principal/devPlace/licensePortalAutoTests/src/main/java/resources/" +
                fileName);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(file);

            // create CSVWriter object fileWriter object as parameter
            CSVWriter writer = new CSVWriter(outputFile, CSVWriter.NO_QUOTE_CHARACTER, ' ',
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

//            // adding header to csv
//            String[] header = { "license key" };
//            writer.writeNext(header);

            // add data to csv
            for (String license: myList) {
                String[] entry = {license};
                writer.writeNext(entry);
            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

        WebElement confirmationModal = driver.findElement(By.className("modal-open"));
        WebElement confirmButton = confirmationModal.findElement(By.xpath("//button[@id='confirm-button']"));
        WebDriverWait clickConfirm = new WebDriverWait(driver, 3);
        clickConfirm.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();

        writeToFile(separateKeys(getGeneratedKeys()), "permanentPurchased.csv");
    }

    @AfterTest()
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }


}
