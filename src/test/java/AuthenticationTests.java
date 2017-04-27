import org.dom4j.DocumentException;
import org.jdom.JDOMException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import rozetkapages.*;
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
    public void checkEmailNotificationOfRegistration() throws IOException, MessagingException, SAXException, JDOMException, DocumentException, InterruptedException {
        Message notifMsg;
        String email="Nedаl@mail.ru";
        String password="h2a221G";
        String userName="Newrcа";
        signUpPage = homePage.openLoginPopUp().openSignUpPage();
        signUpPage.enterUserName(userName);
        signUpPage.enterUserEmail(email);
        signUpPage.enterUserPassword(password);
        personalDataPage = signUpPage.clickOnRegistration();
        Assert.assertEquals(personalDataPage.getTitlePage(), "Личные данные", "Incorrect page title");
        notifMsg=EmailUtilities.getEmailMessage("tserg_2011@mail.ru","Rfhfylfitkm","<sales@rozetka.com.ua>","Подтверждение регистрации");
        Assert.assertTrue(!notifMsg.equals(null), "Email notification isn't sent");
        Assert.assertTrue(notifMsg.getContent().toString().contains("Qwerty1"),"Нет пароля в письме!");
        Assert.assertTrue(notifMsg.getContent().toString().contains("tserg_2011@mail.ru"),"Нет логина в письме!");
        personalDataPage.exit();
        homePage.openUrl(EmailUtilities.getConfirmRegistrationLinkFromEmail(notifMsg));
    }

}
