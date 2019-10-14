package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActivatePage {

    private static String activateTabUrl;

    static {
        activateTabUrl = "http://nxlicensed.hdw.mx/nxlicensed/activation/";
    }

    public static String getAddress() {
        return activateTabUrl;
    }

    public ActivatePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //------------------------
    @FindBy(id = "key")
    private WebElement keyToActivate_inputField;

    public WebElement inputKey() {
        return keyToActivate_inputField;
    }

    //------------------------
    @FindBy(id = "hwid")
    private WebElement hwid_inputField;

    public WebElement inputHwid() {
        return hwid_inputField;
    }

    //------------------------
    @FindBy(xpath = "//input[@name='mode']")
    private WebElement dryRun_checkbox;

    public WebElement markCheckBox() {
        return dryRun_checkbox;
    }

    //------------------------
    @FindBy(xpath = "//input[@value='Submit']")
    private WebElement submit_button;

    public WebElement clickSubmit() {
        return submit_button;
    }


}
