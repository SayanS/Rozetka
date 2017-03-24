package rozetkapages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends Page {
    public SearchResultsPage(WebDriver webDriver){
        super(webDriver);
    }
    public static final String PRODUCTS_TITLE_NAME="//div[@class='g-i-tile-i-title clearfix']/a";

    @FindBy(how= How.XPATH, xpath = PRODUCTS_TITLE_NAME)
    private List<WebElement> productsTitleName=new ArrayList<WebElement>();

    public List<String> getListProductsTitleName(){
        return getTextOfWebElements(PRODUCTS_TITLE_NAME);
    }

}
