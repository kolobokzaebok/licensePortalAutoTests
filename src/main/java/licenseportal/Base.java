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

    public WebDriver initializeDriver() throws IOException {

        // use baseData file to define initial values
        Properties baseProp = new Properties();
        FileInputStream baseFile = new FileInputStream("C:\\projects\\autotests\\src\\main\\java\\resources\\baseData.properties");
        baseProp.load(baseFile);

        //set Browser driver
        String browserName = baseProp.getProperty("browser");
        if (browserName.equals("chrome")) {
            // set driver to Chrome
            System.setProperty("webdriver.chrome.driver", "C:\\devtests\\chromedriver.exe");
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
