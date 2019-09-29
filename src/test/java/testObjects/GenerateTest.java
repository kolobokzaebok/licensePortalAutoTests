package testObjects;


import org.testng.annotations.Test;
import pageObjects.GeneratePage;


import java.io.IOException;

public class GenerateTest extends GeneratePage {
    LoginTest lt = new LoginTest();

    public GenerateTest() throws IOException {
    }

    @Test
    public void generatePermanentPurchased() {
        lt.successfulLogin(getAddress());
    }




}
