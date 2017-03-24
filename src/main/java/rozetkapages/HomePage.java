package rozetkapages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Page{

    public HomePage(WebDriver webDriver){
       super(webDriver);
    }

    LoginPopUp loginPopUp=new LoginPopUp(webDriver);

    private static final String CATALOG_MENU_ITEM=".//li[@name='m-main-i']/a[contains(text(),'$MenuItemName')]";
    private static final String CATALOG_MENU_ITEMS="//a[@name='fat_menu_link']";
    private static final String CATALOG_MENU_BUTTON="//a[@id='fat_menu_btn']";
    private static final String RIGHT_SIDE_MENU_PROMOTION_TITLE="//h2/span[@class='sprite-side main-promotions-title-inner']";
    private static final String CATALOG_MENU_ITEMS_WITH_SUB_ITEMS="//div[@name='second_level']/ancestor::li/a";

    @FindBy(how= How.XPATH, xpath=".//div[@name='rz-header-search-input-text-wrap']/input")
    private WebElement headerSearchField;

    @FindBy(how= How.XPATH, xpath="//button[@name='rz-search-button']")
    private WebElement headerSearchButton;

    @FindBy(how= How.XPATH, xpath=CATALOG_MENU_BUTTON)
    private WebElement catalogMenuButton;

    @FindBy(how= How.XPATH, xpath=RIGHT_SIDE_MENU_PROMOTION_TITLE)
    private WebElement rightSideMenuPromotionTitle;


    public SearchResultsPage headerSearchForText(String text){
        headerSearchField.sendKeys(text);
        headerSearchButton.click();
        return PageFactory.initElements(webDriver, SearchResultsPage.class);
    }

    public HomePage open(String url) throws InterruptedException {
        webDriver.navigate().to(url);
        try{
            webDriver.findElement(By.xpath("//div[contains(text(),'Отказаться')]")).click();
        }catch (Exception e){
        }
         return PageFactory.initElements(webDriver, HomePage.class);
    }

    public ProductPage openProductCategoryPage(String categoryName) throws Exception{
        //((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();",webDriver.findElement(By.xpath(CATALOG_MENU_BUTTON)));
        moveTo(CATALOG_MENU_BUTTON);
        clickOnButton(CATALOG_MENU_BUTTON);
        waitVisabilityOf(CATALOG_MENU_ITEM.replace("$MenuItemName", categoryName));
        moveTo(CATALOG_MENU_ITEM.replace("$MenuItemName", categoryName));
        clickOnButton(CATALOG_MENU_ITEM.replace("$MenuItemName", categoryName));
        return PageFactory.initElements(webDriver, ProductPage.class);
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
        moveTo(CATALOG_MENU_BUTTON);
        subItems=webDriver.findElements(By.xpath(("(.//div[@name='second_level'])["+j+"]//ul//a")));
        for(WebElement we:subItems){
            subItemsHrefs.add(we.getAttribute("href"));
        }
        return subItemsHrefs;
    }

    public ProductPage clickOnSubItemOfCatalog(Integer catalogCategoryIndex, Integer subItemIndex) {
        webDriver.findElement(By.xpath("((.//div[@name='second_level'])["+catalogCategoryIndex+"]//ul//a)["+subItemIndex+"]")).click();
        return PageFactory.initElements(webDriver, ProductPage.class);
    }

    public void moveToCatalogCategoryItem(int j) {
        moveTo("("+CATALOG_MENU_ITEMS+")["+j+"]");
        waitVisabilityOf("(.//div[contains(text(),'Популярные категории')])["+j+"]");
    }


    /*public void getHomePageData() {
        HomePageDataMapping actualHomePageData=new HomePageDataMapping();
        actualHomePageData.setLeftSideMenuCategoryTitle();;
        actualHomePageData.setRightSideMenuPromotionTitle(rightSideMenuPromotionTitle.getText());

    }*/
}
