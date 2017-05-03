package rozetkapages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsPage extends Page{

    public ProductDetailsPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(how=How.XPATH, xpath="//h1")
    private WebElement pageTitle;

    @FindBy(how=How.XPATH, xpath=".//li[@name='characteristics']/a")
    private WebElement characteristicsTab;

    public boolean isLoadedProductDetailsPage(){
        return pageTitle.isDisplayed();
    }

    public ProductDetailsPage clickOnCharacteristicsTab(){
        return new ProductDetailsPage(webDriver);
    }

    public List<String> getCharacteristicValues(){
        List<WebElement> weCharacteristicList;
        List<String> characteristicValues=new ArrayList<String>();
        weCharacteristicList=webDriver.findElements(By.xpath(".//*[@id='pp-characteristics-tab-i']/dd"));
        for(WebElement we:weCharacteristicList){
            characteristicValues.add(we.getText());
        }
        return characteristicValues;
    }

    public boolean isCharacteristicsContains(String searchText) {
        List<WebElement> weCharacteristicList;
        weCharacteristicList=webDriver.findElements(By.xpath(".//*[@id='pp-characteristics-tab-i']/dd"));
        for(WebElement we:weCharacteristicList){
            if(we.getText().toLowerCase().contains(searchText.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
