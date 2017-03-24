import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import rozetkapages.HomePage;
import rozetkautils.ConfigProperties;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 07.03.17.
 */
public abstract class AllureReport implements IHookable{

    @Override
    public void run(IHookCallBack callBack,ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            try
            {createAttachment(testResult.getMethod().getMethodName());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else try
        {createAttachment(testResult.getMethod().getMethodName());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value= "screenshot for {0}", type= "image/png")
    public byte [] createAttachment(String methodName) throws IOException {
        return ((TakesScreenshot) BasicTestCase.webDriver).getScreenshotAs(OutputType.BYTES);
    }
}
