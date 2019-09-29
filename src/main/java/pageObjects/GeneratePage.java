package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.Base;

import java.io.IOException;

public class GeneratePage extends Base {
    private static String generateTabUrl;
    private static WebDriver driver;

    static {
        generateTabUrl = "http://nxlicensed.hdw.mx/nxlicensed/generation/";
    }

    public String getAddress() {
        return generateTabUrl;
    }

    public GeneratePage() throws IOException {
        PageFactory.initElements(driver, this);
    }

    //------------------------
    @FindBy(id = "name")
    private WebElement projectName_inputField;

    public WebElement inputProjectName() {
        return projectName_inputField;
    }

    //------------------------
    @FindBy(id = "company")
    private WebElement company_dropDown;

    public WebElement selectCompany() {
        return company_dropDown;
    }

    //------------------------
    @FindBy(id = "ordertype")
    private WebElement orderType_dropDown;

    public WebElement selectOrderType() {
        return orderType_dropDown;
    }

    //------------------------
    @FindBy(id = "orderid")
    private WebElement order_inputField;

    public WebElement inputOrderId() {
        return order_inputField;
    }

    //------------------------
    @FindBy(id = "authorizedby")
    private WebElement authorizedBy_dropDown;

    public WebElement selectAuthorizedBy() {
        return authorizedBy_dropDown;
    }

    //------------------------
    @FindBy(id = "requestor")
    private WebElement requestor_inputField;

    public WebElement inputRequestor() {
        return requestor_inputField;
    }

    //------------------------
    @FindBy(id = "brand")
    private WebElement brand_dropDown;

    public WebElement selectBrand() {
        return brand_dropDown;
    }

    //------------------------
    @FindBy(id = "class")
    private WebElement type_dropDown;

    public WebElement selectType() {
        return type_dropDown;
    }

    //------------------------
    @FindBy(id = "trialdays")
    private WebElement permanentTime_dropDown;

    public WebElement selectPermanentTime() {
        return permanentTime_dropDown;
    }

    //------------------------
    @FindBy(id = "fixed-expiration-ts")
    private WebElement fixedExpiration_shaded;

    public WebElement selectFixedExpiration() {
        return fixedExpiration_shaded;
    }

    //------------------------
    @FindBy(id = "numpacks")
    private WebElement numberOfPackages_inputField;

    public WebElement inputNumberofPackages() {
        return numberOfPackages_inputField;
    }

    //------------------------
    @FindBy(id = "quantity")
    private WebElement numberOfChannels_inputField;

    public WebElement inputNumberofChannels() {
        return numberOfChannels_inputField;
    }

    //------------------------
    @FindBy(id = "generate-button")
    private WebElement generate_button;

    public WebElement clickGenerate() {
        return generate_button;
    }


}