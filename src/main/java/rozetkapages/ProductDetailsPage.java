package rozetkapages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage extends Page{

    public ProductDetailsPage(WebDriver webDriver){
        super(webDriver);
    }

    @FindBy(how=How.XPATH, xpath="//h1")
    private WebElement pageTitle;


    public boolean isLoadedProductDetailsPage(){
        //waitVisabilityOf(pageTitle);
        return pageTitle.isDisplayed();
    }

}