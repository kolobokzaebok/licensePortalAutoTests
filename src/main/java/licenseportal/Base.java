package licenseportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    private WebDriver driver;
    private int implicitWaitTime;
    private Properties baseProp;
    private String baseUrl;

    public Base() throws IOException {
        this.baseProp = new Properties();
        //Windows:
        //FileInputStream baseFile = new FileInputStream("C:\\projects\\autotests\\src\\main\\java\\resources\\baseData.properties");
        //Linux:
        //FileInputStream baseFile = new FileInputStream("/home/principal/devPlace/licensePortalAutoTests/src/main/java/resources/baseData.properties");
        this.baseProp.load(baseFile);
        this.baseUrl = baseProp.getProperty("url");

    }

    public String getUrl() {
        return this.baseUrl;
    }


    public WebDriver initializeDriver() {

        //set Browser driver
        String browserName = baseProp.getProperty("browser");
        if (browserName.equals("chrome")) {

            // set driver to Chrome
            //Windows:
            //System.setProperty("webdriver.chrome.driver", "C:\\devtests\\chromedriver.exe");
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

}
