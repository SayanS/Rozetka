package rozetkapages;

import org.jsoup.select.Evaluator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Page{

    public HomePage(WebDriver webDriver){
       super(webDriver);
     PageFactory.initElements(webDriver, this);
    }

    LoginPopUp loginPopUp=new LoginPopUp(webDriver);

    private static final String CATALOG_MENU_ITEM=".//li[@name='m-main-i']/a[contains(text(),'$MenuItemName')]";
    private static final String CATALOG_MENU_ITEMS="//a[@name='fat_menu_link']";
    private static final String RIGHT_SIDE_MENU_PROMOTION_TITLE="//h2/span[@class='sprite-side main-promotions-title-inner']";
    private static final String CATALOG_MENU_ITEMS_WITH_SUB_ITEMS="//div[@name='second_level']/ancestor::li/a";

    @FindBy(how= How.XPATH, xpath=".//div[@name='rz-header-search-input-text-wrap']/input")
    private WebElement headerSearchField;

    @FindBy(how= How.XPATH, xpath="//button[@name='rz-search-button']")
    private WebElement headerSearchButton;

    @FindBy(how= How.XPATH, xpath="//a[@id='fat_menu_btn']")
    private WebElement catalogMenuButton;

    @FindBy(how= How.XPATH, xpath=RIGHT_SIDE_MENU_PROMOTION_TITLE)
    private WebElement rightSideMenuPromotionTitle;

    @FindBy(how=How.XPATH, xpath = ".//a[@name='profile']")
    private WebElement userProfileLink;


    public SearchResultsPage headerSearchForText(String text){
        headerSearchField.sendKeys(text);
        headerSearchButton.click();
        return new SearchResultsPage(webDriver);
    }

    public HomePage open(String url) throws InterruptedException {
        webDriver.navigate().to(url);
        waitVisabilityOf("//div[contains(text(),'Отказаться')]");
        try{
            webDriver.findElement(By.xpath("//div[contains(text(),'Отказаться')]")).click();
        }catch (Exception e){
        }
        return new HomePage(webDriver);
    }

    public ProductPage openProductCategoryPage(String categoryName) throws Exception{
        moveTo(catalogMenuButton);
        catalogMenuButton.click();
        waitVisabilityOf(CATALOG_MENU_ITEM.replace("$MenuItemName", categoryName));
        moveTo(CATALOG_MENU_ITEM.replace("$MenuItemName", categoryName));
        clickOnButton(CATALOG_MENU_ITEM.replace("$MenuItemName", categoryName));
        return new ProductPage(webDriver);
    }

    public List<String> getAllCategoryNameOfCatalog() {
        List<String> allCategoryNameOfCatalog;
        allCategoryNameOfCatalog=getTextOfWebElements(CATALOG_MENU_ITEMS);
        return allCategoryNameOfCatalog;
    }

    public void clickOnProductsCatalogButton() throws InterruptedException {
        catalogMenuButton.click();
        waitVisabilityOf("("+CATALOG_MENU_ITEMS+")[1]");
    }

    public String getRightSideMenuPromotionTitle(){
        return rightSideMenuPromotionTitle.getText();
    }

    public String getTextCatalogMenuButton(){
        return catalogMenuButton.getText();
    }

    public int getAmountOfCatalogCategoryWithSubItems() {
        return webDriver.findElements(By.xpath(CATALOG_MENU_ITEMS_WITH_SUB_ITEMS)).size();
    }

    public List<String> getSubItemsHrefsOfCatalogCategory(int j) {
        List<WebElement> subItems=new ArrayList<WebElement>();
        List<String> subItemsHrefs=new ArrayList<String>();
        moveTo(catalogMenuButton);
        subItems=webDriver.findElements(By.xpath(("(.//div[@name='second_level'])["+j+"]//ul//a")));
        for(WebElement we:subItems){
            subItemsHrefs.add(we.getAttribute("href"));
        }
        return subItemsHrefs;
    }

    public ProductPage clickOnSubItemOfCatalog(Integer catalogCategoryIndex, Integer subItemIndex) {
        webDriver.findElement(By.xpath("((.//div[@name='second_level'])["+catalogCategoryIndex+"]//ul//a)["+subItemIndex+"]")).click();
        return new ProductPage(webDriver);
    }

    public void moveToCatalogCategoryItem(int catalogCategoryIndex) throws InterruptedException {
        moveTo("("+CATALOG_MENU_ITEMS+")["+catalogCategoryIndex+"]");
        waitVisabilityOf("((.//div[@name='second_level'])["+catalogCategoryIndex+"]//ul//a)[1]");
    }

    public void moveToProductsCatalogButton() throws InterruptedException {
        moveTo(catalogMenuButton);
        waitVisabilityOf("(//a[@name='fat_menu_link'])[1]");
    }



}
