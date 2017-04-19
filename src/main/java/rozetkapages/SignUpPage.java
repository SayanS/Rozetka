package rozetkapages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends Page {
    public SignUpPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(how = How.XPATH, xpath = ".//input[@name='title']")
    private WebElement userName;

    @FindBy(how = How.XPATH, xpath = ".//div[@class='auth-f-i']/input[@name='email']")
    private WebElement userEmail;

    @FindBy(how = How.XPATH, xpath = ".//input[@name='password']")
    private WebElement userPassword;

    @FindBy(how=How.XPATH, xpath = ".//div[@class='signup-submit']//button[@type='submit']")
    private  WebElement signUpButton;

    public void enterUserName(String userName){
        this.userName.clear();
        this.userName.sendKeys(userName);
    }

    public void enterUserEmail(String userEmail){
        this.userEmail.clear();
        this.userEmail.sendKeys(userEmail);
    }

    public void enterUserPassword(String userPassword){
        this.userPassword.clear();
        this.userPassword.sendKeys(userPassword);
    }

    public PersonalDataPage clickOnRegistration(){
        signUpButton.click();
        return new PersonalDataPage(webDriver);
    }

}
