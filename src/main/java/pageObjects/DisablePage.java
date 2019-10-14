package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DisablePage {
    private static String disableTabUrl;

    static {
        disableTabUrl = "http://nxlicensed.hdw.mx/nxlicensed/disable/";
    }

    public static String getAddress() {
        return disableTabUrl;
    }

    public DisablePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //------------------------
    @FindBy(id = "key")
    private WebElement keyToDisable_inputField;

    public WebElement inputKey() {
        return keyToDisable_inputField;
    }

    //------------------------
    @FindBy(id = "disable")
    private WebElement disable_button;

    public WebElement clickDisable() {
        return disable_button;
    }

    //------------------------
    @FindBy(id = "licenseInfo")
    private WebElement licenseInfo_text;

    public WebElement getLicenseInfo() {
        return licenseInfo_text;
    }


}
