package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ReportPage {

    private static String reportTabUrl;

    static {
        reportTabUrl = "http://nxlicensed.hdw.mx/nxlicensed/activation_report/";
    }

    public static String getAddress() {
        return reportTabUrl;
    }

    public ReportPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //------------------------
    @FindBy(id = "company")
    private WebElement company_dropDown;

    public Select selectCompany() {
        return new Select(company_dropDown);
    }

    //------------------------
    @FindBy(id = "reportrange")
    private WebElement calendar;

    public WebElement useCalendar() {
        return calendar;
    }

    //------------------------
    @FindBy(xpath = "/html/body/div[4]/div[1]/ul/li[4]")
    private WebElement range;

    public WebElement chooseRange() {
        return range;
    }

    //------------------------
    @FindBy(id = "getinto")
    private WebElement generateReport_button;

    public WebElement clickGenerateRange() {
        return generateReport_button;
    }

    //------------------------
    @FindBy(xpath = "//*[@id=\"reporttable\"]/thead")
    private WebElement downloaded_header;

    public WebElement checkHeader() {
        return downloaded_header;
    }



}
