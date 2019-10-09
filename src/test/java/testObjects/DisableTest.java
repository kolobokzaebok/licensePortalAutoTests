package testObjects;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.DisablePage;
import pageObjects.LoginPage;
import resources.DriverInit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisableTest {
    private WebDriver driver;
    private DisablePage dp;

    public DisableTest() throws IOException {
        DriverInit dr = new DriverInit();
        this.driver = dr.initializeDriver();
        this.dp = new DisablePage(this.driver);
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
    private Object[] getKeys() throws IOException {
        String pathToCsv = "/home/principal/devPlace/licensePortalAutoTests/src/main/java/resources/permanentPurchased.csv";
        File csvFile = new File(pathToCsv);
        String[] data;
        List<String> myArr = new ArrayList<>();

        if (csvFile.isFile()) {
            BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
            String row;
            while ((row = csvReader.readLine()) != null) {
                data = row.split(" ");
                myArr.add(data[1]);
            }
            csvReader.close();
        }

        return myArr.toArray();

    }

}
