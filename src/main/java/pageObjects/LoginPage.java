package pageObjects;

import licenseportal.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage extends Base {
    public WebDriver driver;

    public LoginPage() throws IOException {
        this.driver = super.initializeDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//input[@name='email']")
    private
    WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    private
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private
    WebElement submit;

    public WebElement submitEmail() {
        return email;
    }

    public WebElement submitPassword() {
        return password;
    }

    public WebElement submitSubmit() {
        return submit;
    }

}
