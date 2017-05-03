
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rozetkadata.HomePageDataMapping;
import rozetkapages.*;
import rozetkautils.ConfigProperties;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Title("Test Suite -> Smoke test cases")
@Description("This Suite contains testcases for Smoke testing")
public class SmokeTests extends BasicTestCase {
    private SearchResultsPage searchResultsPage;
    private ProductPage productPage;
    private ProductDetailsPage productDetailsPage;
    public HomePage homePage=new HomePage(getWebDriver());

   @BeforeTest
    public void setUp() throws InterruptedException {
        homePage=homePage.open(ConfigProperties.getProperty("baseURL"));
    }

    @Features("Page functionality")
    @Stories("Checking load functionality")
    @Title("Is Home page loaded")
    @Description("This testcase checks that some elements of Home page are loaded")
    @Severity(SeverityLevel.BLOCKER)
    @Test(enabled = true)
    public void isHomePageLoaded() throws FileNotFoundException, YamlException, InterruptedException {
        YamlReader reader = new YamlReader(new FileReader(ConfigProperties.getProperty("testData.HomePageMapping")));
        HomePageDataMapping expectedHomePageData = new HomePageDataMapping();
        expectedHomePageData = reader.read(HomePageDataMapping.class);

        Assert.assertEquals(homePage.getRightSideMenuPromotionTitle(), expectedHomePageData.getRightSideMenuPromotionTitle());
        Assert.assertEquals(homePage.getTextCatalogMenuButton(), expectedHomePageData.getLeftSideMenuCategoryTitle());
        Assert.assertEquals(homePage.getAllCategoryNameOfCatalog(), expectedHomePageData.getleftSideMenuCategoryItems());
    }

    @Features("Elements functionality")
    @Stories("Checking links functionality")
    @Title("Catalog items links")
    @Description("This testcase checks that click on each item of Catalog menu is opened appropriate Url")
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true)
    public void isCatalogItemOpenAppropriatePage() throws Exception {
        YamlReader reader = new YamlReader(new FileReader(ConfigProperties.getProperty("testData.leftSideMenuCategoryItems")));
        Map<String, String> catalogCategoryUrl = (Map<String, String>) reader.read();

        homePage.clickOnProductsCatalogButton();

        for (Map.Entry<String, String> category : catalogCategoryUrl.entrySet()) {
            productPage = homePage.openProductCategoryPage(category.getKey());
            productPage.waitProductCategoryPage();

            saveTextLog(category.getValue(), productPage.getUrl());
            createScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());

            Assert.assertEquals(category.getValue(), productPage.getUrl());
        }
    }

    @Features("Elements functionality")
    @Stories("Checking search functionality")
    @Title("Search functionality")
    @Description("This testcase checks search functionality by Product Title Name")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void headerSearchByProductTitleName() throws InterruptedException, IOException {
        String productName = "Lenovo";
        homePage.headerSearch(productName);

        for (String productTitleName : searchResultsPage.getAllProductTitleName()) {
            createScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertTrue(productTitleName.contains(productName), productTitleName + " isn't contained " + productName);
        }
    }

    @Step("Check that all links of Sub items of Catalog is clickable and opened appropreate URL")
    @Test(enabled = true)
    public void checkSubItemsLinksOfCatalog() throws IOException, InterruptedException {
        List<String> allSubItemsHrefs = new ArrayList<String>();

        for (int j = 1; j <= homePage.getAmountOfCatalogCategoryWithSubItems(); j++) {
            homePage.moveToProductsCatalogButton();
            homePage.moveToCatalogCategoryItem(j);
            allSubItemsHrefs = homePage.getSubItemsHrefsOfCatalogCategory(j);
            for (int i=1;i<=allSubItemsHrefs.size()+1;i++) {
                homePage.moveToProductsCatalogButton();
                homePage.moveToCatalogCategoryItem(j);
                productPage =homePage.clickOnSubItemOfCatalog(j, i);
                productPage.waitProductCategoryPage();
                try {
                    Assert.assertEquals(homePage.getCurrentUrl(), allSubItemsHrefs.get(i-1));
                }catch (Exception e){
                    createScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
                }
            }
        }
    }
    @Features("Product details page")
    @Title("Is Product details page displayed")
    @Test(enabled = true)
    public void isClickOnTitleOfProductCardOpenProductDetailPage() throws InterruptedException, IOException {
        homePage.moveToCatalogCategoryItem(1);
        productPage= homePage.clickOnSubItemOfCatalog(1, 28);
        productDetailsPage=productPage.clickOnTitleOfProductCard(2);
        createScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
        Assert.assertTrue(productDetailsPage.isLoadedProductDetailsPage(), "Page isn't loaded!!!!");
    }
    @Test
    public void checkSearchInCategory() throws InterruptedException {
        String searchText="Сальник";
        homePage.selectCategoryOfHeaderSearch(12);
        searchResultsPage=homePage.headerSearch(searchText);
        Assert.assertEquals(searchText,searchResultsPage.getTitleSearchFor(), "Title should be "+searchText+" but "+searchResultsPage.getTitleSearchFor());
        productDetailsPage=searchResultsPage.openProductDetailsPageOfProduct(93);
        //productDetailsPage.isLoadedProductDetailsPage();
        productDetailsPage.clickOnCharacteristicsTab();
        Assert.assertTrue(productDetailsPage.isCharacteristicsContains(searchText), "Nither of the Characteristics contains "+searchText);
    }

}
