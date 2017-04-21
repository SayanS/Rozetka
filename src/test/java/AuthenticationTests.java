import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
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
    private HomePage homePage = new HomePage(getWebDriver());
    private LoginPopUp loginPopUp;
    private SignUpPage signUpPage;
    private PersonalDataPage personalDataPage;


    @BeforeTest
    public void setUp() throws InterruptedException {
        homePage = homePage.open(ConfigProperties.getProperty("baseURL"));
    }

    @Test
    public void checkEmailNotificationOfRegistration() throws IOException, MessagingException, SAXException {
        Message notifMsg;
        String email="tserg_2011@mail.ru";
        String password="Rfhfylfitkm";
        String userName="UserName";
        signUpPage = homePage.openLoginPopUp().openSignUpPage();
        signUpPage.enterUserName(userName);
        signUpPage.enterUserEmail(email);
        signUpPage.enterUserPassword(password);
        personalDataPage = signUpPage.clickOnRegistration();
        Assert.assertEquals(personalDataPage.getTitlePage(), "Личные данные", "Incorrect page title");
        notifMsg=EmailUtilities.getEmailMessage(email,password,"<sales@rozetka.com.ua>","Подтверждение регистрации");
        Assert.assertTrue(!notifMsg.equals(null), "Email notification isn't sent");
        Assert.assertTrue(notifMsg.getContent().toString().contains(password),"Нет пароля в письме!");
        Assert.assertTrue(notifMsg.getContent().toString().contains(email),"Нет логина в письме!");
        EmailUtilities.clickOnConfirmRegistration(notifMsg);
    }

}
