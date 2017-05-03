package rozetkapages;


import org.jsoup.select.Evaluator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends Page {
    public SearchResultsPage(WebDriver webDriver){
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(how=How.XPATH, xpath = ".//h1/span[@id='search_result_title_text']")
    private WebElement titleSearchFor;


    @FindBy(how= How.XPATH, xpath = "//div[@class='g-i-tile-i-title clearfix']/a")
    private List<WebElement> productsTitleName=new ArrayList<WebElement>();

    public List<String> getAllProductTitleName(){
        List<String> allProductTitleName=new ArrayList<String>();
        for(WebElement we:productsTitleName){
            allProductTitleName.add(we.getText());
        }
        return allProductTitleName;
    }

    public  String getTitleSearchFor(){
        return titleSearchFor.getText();
    }

    public ProductDetailsPage openProductDetailsPageOfProduct(Integer index) throws InterruptedException {
        Integer pageNumber;
        pageNumber=(int)index/32;
        if(pageNumber>0){
            for (int i=0;i<=pageNumber;i++){
                ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView(true);", webDriver.findElement(By.xpath(".//div[@name='more']/a")));
                webDriver.findElement(By.xpath(".//div[@name='more']/a/img")).click();
                (new WebDriverWait(webDriver, 15)).until(ExpectedConditions.invisibilityOf(webDriver.findElement(By.xpath("//div[contains(@style,\"width: 100%; height: 100%; background-color: rgb(255, 255, 255)\")]"))));
            }
            webDriver.findElement(By.xpath("(//img[contains(@src,'rozetka.ua/goods')])["+index%32+"]")).click();
        }else{
            webDriver.findElement(By.xpath("(//img[contains(@src,'rozetka.ua/goods')])["+index+"]")).click();
        }
        Thread.sleep(5000);
        return new ProductDetailsPage(webDriver);
    }



}
