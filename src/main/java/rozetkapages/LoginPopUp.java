package rozetkapages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPopUp extends Page{
    public LoginPopUp(WebDriver webDriver){
        super(webDriver);
       PageFactory.initElements(webDriver, this);
    }

    @FindBy(how=How.XPATH, xpath = ".//input[@name='login']")
    private WebElement loginField;

    @FindBy(how=How.XPATH, xpath = ".//input[@name='password']")
    private WebElement passwordField;

    @FindBy(how=How.XPATH, xpath = "//button[@name='auth_submit']")
    private WebElement loginButton;

    @FindBy(how= How.XPATH, xpath = "//div[@name='simple_auth']//a[@name='close']" )
    private WebElement cancelButton;

    public void inputLogin(String login){
        loginField.clear();
        loginField.sendKeys(login);
    }

    public void inputPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void closeLoginPopUp(){
        cancelButton.click();
    }

    public HomePage openPostLoginHomePage(){
        loginButton.click();
        return new HomePage(webDriver);
    }


}
