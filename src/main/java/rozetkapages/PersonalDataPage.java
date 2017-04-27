package rozetkapages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PersonalDataPage extends Page{

    public PersonalDataPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(how= How.XPATH, xpath = ".//div[@id='personal_information']//h1")
    private WebElement titlePage;

    @FindBy(how= How.XPATH, xpath = "(.//div[@class='profile-col-main']//div[@class='profile-f-i-field'])[1]")
    private WebElement userName;

    @FindBy(how= How.XPATH, xpath = "(.//div[@class='profile-col-main']//div[@class='profile-f-i-field'])[2]")
    private WebElement userEmail;

    @FindBy(how=How.XPATH, xpath = "//a[@id='profile_signout']")
    private  WebElement exitButton;

    public String getTitlePage(){
        return titlePage.getText();
    }

    public void exit(){
        exitButton.click();
    }


}
