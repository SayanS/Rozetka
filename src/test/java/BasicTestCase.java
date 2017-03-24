import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.IHookable;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import rozetkapages.HomePage;
import rozetkautils.ConfigProperties;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class BasicTestCase {
    protected static WebDriver webDriver;
    public HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

    public WebDriver getWebDriver(){

        if(webDriver==null){
            webDriver=new ChromeDriver();

            webDriver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("impl.wait")), TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
        }
        return  webDriver;
    }

    @BeforeMethod
    public void setUp() throws InterruptedException {
        homePage.open(ConfigProperties.getProperty("baseURL"));
    }

    @AfterTest
    public void tearDown(){
       webDriver.quit();
    }

    @Attachment(value = "{0}", type = "text/plain")
    public String saveTextLog(String expectedCatalogItemName, String actualCatalogItemName) {
        return actualCatalogItemName;
    }

    @Attachment(value = "screenshot for {0}", type = "image/png")
    public byte[] createScreenShot(String methodName) throws IOException {
        return ((TakesScreenshot) BasicTestCase.webDriver).getScreenshotAs(OutputType.BYTES);
    }


}