package resources;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class DriverInit extends Base{
    private static WebDriver driver;

    public DriverInit() throws IOException {
    }

    public WebDriver initializeDriver() {
        // use baseData,properties file -- option I
        //String browserName = baseProp.getProperty("browser");
        // use maven cli to specify browser's name -- option II
        // syntax of the command: mvn test -Dbrowser=chrome
        String browserName = System.getProperty("browser");

        if (browserName.contains("chrome")) {
            // set driver to Chrome
            System.setProperty("webdriver.chrome.driver", getDriverLocation());

            if (browserName.contains("headless")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                driver = new ChromeDriver(options);
            } else {
                driver = new ChromeDriver();
            }

        } else if (browserName.equals("firefox")) {
            // initialize driver as firefox
        } else {
            // initialize driver with a default value
        }

        driver.manage().timeouts().implicitlyWait(getImplicitWaitTime(), TimeUnit.SECONDS);

        return driver;
    }

    // capture screenshot method
    public void getScreenshot(String name) throws IOException {
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File dest;
        dest = new File(String.format(getScrnDirPath() + "%s.png", name));
        FileUtils.copyFile(src, dest);
    }

}
