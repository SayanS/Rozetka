import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rozetkapages.HomePage;
import rozetkapages.LoginPopUp;
import rozetkapages.PersonalDataPage;
import rozetkapages.SignUpPage;
import rozetkautils.ConfigProperties;
import rozetkautils.EmailUtilities;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.Array;


public class AuthenticationTests extends BasicTestCase {
    private HomePage homePage=new HomePage(getWebDriver());
    private LoginPopUp loginPopUp;
    private SignUpPage signUpPage;
    private PersonalDataPage personalDataPage;


    @BeforeTest
    public void setUp() throws InterruptedException {
        homePage = homePage.open(ConfigProperties.getProperty("baseURL"));
    }

    @Test
    public void checkEmailNotificationOfRegistration() throws IOException, MessagingException {
        Message[] messages;
        signUpPage=homePage.openLoginPopUp().openSignUpPage();
        signUpPage.enterUserName("Uidddfiii");
        signUpPage.enterUserEmail("hsjddhdkdjshds@gmail.com");
        signUpPage.enterUserPassword("Qwerty1Q");
        personalDataPage=signUpPage.clickOnRegistration();
        Assert.assertEquals(personalDataPage.getTitlePage(),"Личные данные", "Incorrect page title");
        messages=EmailUtilities.getEmailMessages("imap.mail.ru", "tserg_2011@mail.ru", "Rfhfylfitkm");

    }

}
