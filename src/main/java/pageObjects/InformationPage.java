package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InformationPage {

    private static String infoTabUrl;

    static {
        infoTabUrl = "http://nxlicensed.hdw.mx/nxlicensed/info/";
    }

    public static String getAddress() {
        return infoTabUrl;
    }

    public InformationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //------------------------
    @FindBy(id = "key")
    private WebElement key_input;

    public WebElement inputKey() {
        return key_input;
    }

    //------------------------
    @FindBy(id = "getinfo")
    private WebElement getInfo_submit;

    public WebElement clickGetInfo() {
        return getInfo_submit;
    }

    //------------------------
    @FindBy(id = "licenseInfo")
    private WebElement currentInfo;

    public WebElement checkProperKey() {
        return currentInfo;
    }

}
