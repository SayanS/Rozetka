package rozetkapages;


import org.jsoup.select.Evaluator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends Page {
    public SearchResultsPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(how= How.XPATH, xpath = "//div[@class='g-i-tile-i-title clearfix']/a")
    private List<WebElement> productsTitleName=new ArrayList<WebElement>();

    public List<String> getAllProductTitleName(){
        List<String> allProductTitleName=new ArrayList<String>();
        for(WebElement we:productsTitleName){
            allProductTitleName.add(we.getText());
        }
        return allProductTitleName;
    }

}
