package resources;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Base {
    private static WebDriver driver;
    private int implicitWaitTime;
    private Properties baseProp;
    private String baseUrl;
    private String baseEmail;
    private String basePassword;
    private static String osName;
    private static String localDirectoryName;

    // Base Constructor
    public Base() throws IOException {
        FileInputStream baseFile;
        this.osName = System.getProperty("os.name").toLowerCase();
        this.localDirectoryName = System.getProperty("user.dir");
        this.baseProp = new Properties();

        if (osName.equals("linux")) {
            //OS: Linux
            baseFile = new FileInputStream(localDirectoryName + "/src/main/java/resources/baseData.properties");
        } else {
            //OS: Windows
            baseFile = new FileInputStream(localDirectoryName + "\\src\\main\\java\\resources\\baseData.properties");
        }

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
        // use baseData,properties file -- option I
        //String browserName = baseProp.getProperty("browser");
        // use maven cli to specify browser's name -- option II
        // syntax of the command: mvn test -Dbrowser=chrome
        String browserName = System.getProperty("browser");

        String driverLocation;
        if (browserName.contains("chrome")) {
            // set driver to Chrome
            if (osName.equals("linux")) {
                //OS: Linux
                driverLocation = localDirectoryName + "/src/main/java/resources/chromedriver";
            } else {
                //OS: Windows
                driverLocation = localDirectoryName + "\\src\\main\\java\\resources\\chromedriver";
            }
            System.setProperty("webdriver.chrome.driver", driverLocation);

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

        // once initialized, a global default implicit timeout can be set (in seconds)
        implicitWaitTime = Integer.parseInt(baseProp.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);

        return driver;
    }

    // capture screenshot method
    public static void getScreenshot(String name) throws IOException {
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File dest;
        if (osName.equals("linux")) {
            //OS:Linux
            dest = new File(String.format(localDirectoryName + "/screenshots/%s.png", name));
        } else {
            //OS:Windows
            dest = new File(String.format(localDirectoryName + "\\screenshots\\%s.png", name));
        }
        FileUtils.copyFile(src, dest);
    }

}










































