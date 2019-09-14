package resources;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Base {
    private WebDriver driver;
    private int implicitWaitTime;
    private Properties baseProp;
    private String baseUrl;
    private String baseEmail;
    private String basePassword;

    // Base Constructor
    public Base() throws IOException {
        this.baseProp = new Properties();
        //Windows:
        FileInputStream baseFile = new FileInputStream("C:\\projects\\autotests\\src\\main\\java\\resources\\baseData.properties");
        //Linux:
        //FileInputStream baseFile = new FileInputStream("/home/principal/devPlace/licensePortalAutoTests/src/main/java/resources/baseData.properties");
        this.baseProp.load(baseFile);
        this.baseUrl = baseProp.getProperty("url");
        this.baseEmail = baseProp.getProperty("login");
        this.basePassword = baseProp.getProperty("password");

    }

    // Login Page data
    public String getUrl() {
        return this.baseUrl;
    }
    public String getEmail() {
        return this.baseEmail;
    }
    public String getPassword() {
        return this.basePassword;
    }

    //set Browser driver
    public WebDriver initializeDriver() {

        String browserName = baseProp.getProperty("browser");
        if (browserName.equals("chrome")) {

            // set driver to Chrome
            //Windows:
            System.setProperty("webdriver.chrome.driver", "C:\\devtests\\chromedriver.exe");
            //Linux:
            //System.setProperty("webdriver.chrome.driver", "/home/principal/javaDev/chromedriver");
            driver = new ChromeDriver();

        } else if (browserName.equals("firefox")) {
            // initialize driver as firefox
        } else {
            // initialize driver with a default value
        }

        // once initialized, a global default implicit timeout can be set (in seconds)
        implicitWaitTime = Integer.parseInt(baseProp.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);

        return driver;
    }

    // capture screenshot method
    public void getScreenshot(String name) throws IOException {
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(String.format("C:\\projects\\autotests\\target\\screenshots\\%s.png", name));
        FileUtils.copyFile(src, dest);
    }

}










































