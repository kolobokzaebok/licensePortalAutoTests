package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class GeneratePage {
    private static String generateTabUrl;

    static {
        generateTabUrl = "http://nxlicensed.hdw.mx/nxlicensed/generation/";
    }

    public static String getAddress() {
        return generateTabUrl;
    }

    public GeneratePage(WebDriver driver) {
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

    public Select selectCompany() {
        return new Select(company_dropDown);
    }

    //------------------------
    @FindBy(id = "ordertype")
    private WebElement orderType_dropDown;

    public Select selectOrderType() {
        return new Select(orderType_dropDown);
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

    public Select selectAuthorizedBy() {
        return new Select(authorizedBy_dropDown);
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

    public Select selectBrand() {
        return new Select(brand_dropDown);
    }

    //------------------------
    @FindBy(id = "class")
    private WebElement type_dropDown;

    public Select selectType() {
        return new Select(type_dropDown);
    }

    //------------------------
    @FindBy(id = "trialdays")
    private WebElement permanentTime_dropDown;

    public Select selectPermanentTime() {
        return new Select(permanentTime_dropDown);
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