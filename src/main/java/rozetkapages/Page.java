package rozetkapages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class Page {
    protected WebDriver webDriver;

    public Page(WebDriver webDriver){
        this.webDriver=webDriver;
    }

    public List<String> getTextOfWebElements(String webElementXpath) {
        List<String> webElementsText = new ArrayList<String>();
        for (WebElement we : webDriver.findElements(By.xpath(webElementXpath))) {
            webElementsText.add(we.getText());
        }
        return webElementsText;
    }

    public void clickOnButton(String xpathButton) {
        webDriver.findElement(By.xpath(xpathButton)).click();
    }

    public WebElement fluentWait(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return foo;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void moveTo(String xpath) {
        Actions action = new Actions(webDriver);
        action.moveToElement(webDriver.findElement(By.xpath(xpath))).perform();
    }

    public void moveTo(WebElement webElement) {
        Actions action = new Actions(webDriver);
        action.moveToElement(webElement).perform();
    }

    public boolean waitVisabilityOf(String xpath) {
        Boolean flag;
        try {
            (new WebDriverWait(webDriver, 30)).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(xpath))));
            flag = webDriver.findElement(By.xpath(xpath)).isDisplayed();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }


}
