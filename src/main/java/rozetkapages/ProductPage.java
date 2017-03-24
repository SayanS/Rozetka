package rozetkapages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends Page {
    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static final String FILTER_BLOCKS = ".//*[@name='filter_parameters_block']";
    public static final String FILTER_BLOCK_TITLE = ".//*[@name='filter_parameters_title']";
    public static final String FILTER_BLOCK_OPTIONS = "//div[contains(@class,'filter-parametrs-i-l-i')]";
    public static final String FILTER_BLOCK_TRACK_BAR = ".//div[@id='trackbarprice']";
    public static final String FILTER_BLOCK_OPTIONS_PRODUCTS_COUNT = ".//i[@class='filter-parametrs-i-l-i-default-count']";
    public static final String FILTER_BLOCK_OPTIONS_MIN_VALUE = "(.//*[@class='input-text'])[1]";
    public static final String FILTER_BLOCK_OPTIONS_MAX_VALUE = "(.//*[@class='input-text'])[2]";
    public static final String FILTER_BLOCK_OPTIONS_OK_BUTTON = ".//button[@type='submit']";
    public static final String FILTER_BLOCK_SHOW_MORE_BUTTON = "//a[@name='show_more_parameters']";
    public static final String FILTER_BLOCK_HIDE_MORE_BUTTON = "//a[@name='show_more_parameters']";
    public static final String FILTER_BLOCK_EXPAND_CLOSE = ".//div[@class='filter-parametrs-i-header']";

    public static final String PRODUCT_CARDS = ".//div[@class='g-i-tile-i-box-desc']";
    //public static final String PRODUCT_CARDS_TITLE="//div[@class='g-i-tile-i-title clearfix']/a";
    public static final String PRODUCT_CARDS_ACTUAL_PRICE = "//div[contains(@id,'goods_price')]/div[last()]";


    @FindBy(how = How.XPATH, xpath = "//h1")
    public WebElement pageTitle;

    @FindBy(how = How.XPATH, xpath = "//div[@class='g-i-tile-i-title clearfix']/a")
    public List<WebElement> productCardTitleList = new ArrayList<WebElement>();

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void waitProductCategoryPage() {
        waitVisabilityOf("//h1");
    }

    public Integer getAmountOfFilterBlocks() {
        return webDriver.findElements(By.xpath(FILTER_BLOCKS)).size();
    }

    public List<WebElement> getFilterBlocks() {
        return webDriver.findElements(By.xpath(FILTER_BLOCKS));
    }

    public WebElement getFilterBlock(Integer blockIndex) {
        return webDriver.findElement(By.xpath("(" + FILTER_BLOCKS + ")[" + blockIndex + "]"));
    }

    public String checkFilterOption(Integer blockIndex, Integer optionIndex) {
        return getFilterBlock(blockIndex).findElement(By.xpath("(" + FILTER_BLOCK_OPTIONS + ")[" + optionIndex + "]"))
                .findElement(By.xpath(FILTER_BLOCK_TITLE)).getText();
    }

    public boolean isFilterBlockContainTrackBar(Integer blockIndex) {
        try {
            getFilterBlock(blockIndex).findElements(By.xpath(FILTER_BLOCK_TITLE));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public Integer getProductsAmountOfFilterBlockOption(Integer blockIndex, Integer optionIndex) {
        return Integer.getInteger(getFilterBlock(blockIndex)
                .findElement(By.xpath("(" + FILTER_BLOCK_OPTIONS + ")[" + optionIndex + "]"))
                .findElement(By.xpath(FILTER_BLOCK_OPTIONS_PRODUCTS_COUNT))
                .getText().replace("(", "").replace(")", ""));
    }

    public void clickOnOkButtonOfFilterBlock(Integer blockIndex) {
        getFilterBlock(blockIndex).findElement(By.xpath(FILTER_BLOCK_OPTIONS_OK_BUTTON)).click();
    }

    public void inputMinValueOfFilterBlockOption(Integer blockIndex, Integer minValue) {
        getFilterBlock(blockIndex).findElement(By.xpath(FILTER_BLOCK_OPTIONS_MIN_VALUE)).clear();
        getFilterBlock(blockIndex).findElement(By.xpath(FILTER_BLOCK_OPTIONS_MIN_VALUE)).sendKeys(minValue.toString());
    }

    public void inputMaxValueOfFilterBlockOption(Integer blockIndex, Integer maxValue) {
        getFilterBlock(blockIndex).findElement(By.xpath(FILTER_BLOCK_OPTIONS_MAX_VALUE)).clear();
        getFilterBlock(blockIndex).findElement(By.xpath(FILTER_BLOCK_OPTIONS_MAX_VALUE)).sendKeys(maxValue.toString());
    }

    public Integer getMinValueOfFilterBlockOption(Integer blockIndex) {
        return Integer.getInteger(getFilterBlock(blockIndex).findElement(By.xpath(FILTER_BLOCK_OPTIONS_MIN_VALUE)).getText());
    }

    public Integer getMaxValueOfFilterBlockOption(Integer blockIndex) {
        return Integer.getInteger(getFilterBlock(blockIndex).findElement(By.xpath(FILTER_BLOCK_OPTIONS_MAX_VALUE)).getText());
    }

    public void clickOnShowMoreBlockOptions(Integer blockIndex) {
        try {
            getFilterBlock(blockIndex).findElement(By.xpath(FILTER_BLOCK_SHOW_MORE_BUTTON)).click();
        } catch (Exception e) {
        }
    }

    public void clickOnHideMoreBlockOptions(Integer blockIndex) {
        try {
            getFilterBlock(blockIndex).findElement(By.xpath(FILTER_BLOCK_HIDE_MORE_BUTTON)).click();
        } catch (Exception e) {
        }
    }

    public List<WebElement> getAllCurrentProductCards() {
        return webDriver.findElements(By.xpath(PRODUCT_CARDS));
    }

    public List<String> getAllProductsTitleFromCurrentPage() {
        List<String> productTitleNamesList = new ArrayList<String>();
        for (WebElement productTitle : productCardTitleList) {
            productTitleNamesList.add(productTitle.getText());
        }
        return productTitleNamesList;
    }

    public ProductDetailsPage clickOnTitleOfProductCard(Integer cardIndex) {
        productCardTitleList.get(cardIndex).click();
        return PageFactory.initElements(webDriver, ProductDetailsPage.class);
    }

}
