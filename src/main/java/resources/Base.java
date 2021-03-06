package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class Base {
    private String baseUrl;
    private String baseEmail;
    private String basePassword;
    private String driverLocation;
    private String scrnDirPath;
    private String dwnloadDirPath;
    private String csvPermPurchPath;
    private String pathToResources;
    private int implicitWaitTime;

    // Base Constructor
    public Base() throws IOException {
        FileInputStream baseFile;
        String osName = System.getProperty("os.name").toLowerCase();
        String localDirectoryName = System.getProperty("user.dir");
        Properties baseProp = new Properties();
        String srcLin = "/src/main/java/resources/baseData.properties";
        String srcWin = "\\src\\main\\java\\resources\\baseData.properties";
        String drvLin = "/src/main/java/resources/chromedriver";
        String drvWin = "\\src\\main\\java\\resources\\cdriver.exe";
        String scrnLin = "/screenshots/";
        String scrnWin = "\\screenshots\\";
        String dwnloadLin = "/downloads/";
        String dwnloadWin = "\\downloads\\";
        String csvPermPurchLin = "/src/main/java/resources/permanentPurchased.csv";
        String csvPermPurchWin = "\\src\\main\\java\\resources\\permanentPurchased.csv";
        String resourcesLin = "/src/main/java/resources/";
        String resourcesWin = "\\src\\main\\java\\resources\\";

        if (osName.equals("linux")) {
            //OS: Linux
            baseFile = new FileInputStream(localDirectoryName + srcLin);
            driverLocation = localDirectoryName + drvLin;
            scrnDirPath = localDirectoryName + scrnLin;
            dwnloadDirPath = localDirectoryName + dwnloadLin;
            csvPermPurchPath = localDirectoryName + csvPermPurchLin;
            pathToResources = localDirectoryName + resourcesLin;
        } else {
            //OS: Windows
            baseFile = new FileInputStream(localDirectoryName + srcWin);
            driverLocation = localDirectoryName + drvWin;
            scrnDirPath = localDirectoryName + scrnWin;
            dwnloadDirPath = localDirectoryName + dwnloadWin;
            csvPermPurchPath = localDirectoryName + csvPermPurchWin;
            pathToResources = localDirectoryName + resourcesWin;
        }

        baseProp.load(baseFile);
        this.baseUrl = baseProp.getProperty("url");
        this.baseEmail = baseProp.getProperty("login");
        this.basePassword = baseProp.getProperty("password");
        this.implicitWaitTime = Integer.parseInt(baseProp.getProperty("implicitWait"));
    }

    //return path to a driver (Chrome)
    public String getDriverLocation() {
        return driverLocation;
    }

    //return path to Screenshot(in case of failure) folder
    public String getScrnDirPath() {
        return scrnDirPath;
    }

    //return path to Downloads folder
    public String getDwnloadDirPath() {
        return dwnloadDirPath;
    }

    //return path to Permanent Purchased .csv
    public String getCsvPermPurchPath() {
        return csvPermPurchPath;
    }

    //return path to resources directory
    public String getpathToResources() {
        return pathToResources;
    }

    //return implicit wait time in seconds -- global
    public int getImplicitWaitTime() {
        return implicitWaitTime;
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

}










































