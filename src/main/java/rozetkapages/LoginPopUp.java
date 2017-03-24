package rozetkapages;

import org.openqa.selenium.WebDriver;

public class LoginPopUp extends Page{
    public LoginPopUp(WebDriver webDriver){
        super(webDriver);
    }
    private static final String CANCEL_BUTTON="//div[@name='simple_auth']//a[@name='close']";

    public void closeLoginPopUp(){
        clickOnButton(CANCEL_BUTTON);
    }

}
