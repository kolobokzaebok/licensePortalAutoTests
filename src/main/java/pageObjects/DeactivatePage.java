package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DeactivatePage {

    private static String deactivateTabUrl;

    static {
        deactivateTabUrl = "http://nxlicensed.hdw.mx/nxlicensed/deactivation/";
    }

    public static String getAddress() {
        return deactivateTabUrl;
    }

    public DeactivatePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //------------------------
    @FindBy(id = "autodeact_reason")
    private WebElement reason_dropDown;

    public Select selectReason() {
        return new Select(reason_dropDown);
    }

    //------------------------
    @FindBy(id = "new_hwid")
    private WebElement newHwid_inputField;

    public WebElement inputNewHwid() {
        return newHwid_inputField;
    }

    //------------------------
    @FindBy(id = "integrator")
    private WebElement integrator_inputField;

    public WebElement inputIntegrator() {
        return integrator_inputField;
    }

    //------------------------
    @FindBy(id = "end_user")
    private WebElement endUser_inputField;

    public WebElement inputEndUser() {
        return endUser_inputField;
    }

    //------------------------
    @FindBy(id = "submit-button")
    private WebElement deactivate_button;

    public WebElement clickDeactivateNow() {
        return deactivate_button;
    }

    //------------------------
    @FindBy(xpath = "//*[@id=\"license-key-block-1\"]/div/span[1]")
    private WebElement plus_sign;

    public WebElement clickPlus() {
        return plus_sign;
    }

    //------------------------
    @FindBy(id = "title")
    private WebElement legend;

    public WebElement checkLegend() {
        return legend;
    }


}
