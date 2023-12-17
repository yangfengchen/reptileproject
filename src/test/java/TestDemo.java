import com.ddzj.proxyip.IpMain;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class TestDemo {
    private static Logger logger = LoggerFactory.getLogger(TestDemo.class);

    public static void main(String[] args) {
        //页面加载策略
        //标准	完整	默认使用，等待所有资源下载
        String str = IpMain.getPorxy();
        Proxy proxy = new Proxy();
        //proxy.setHttpProxy(str).setFtpProxy(str).setSslProxy(str).setSocksProxy(str);
        proxy.setHttpProxy(str);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("proxy", proxy);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver(chromeOptions);
        try{
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");
            //您可以请求许多关于浏览器的信息类型，包括窗口句柄、浏览器大小/位置、cookie、警报等。
            String title = driver.getTitle();
            logger.debug("Web form:{}", title);
            // 本质上，在您尝试定位它之前，您要确保该元素在页面上，并且在您尝试与它交互之前，该元素处于可交互状态。
            //隐式等待很少是最好的解决方案，但它是这里最容易演示的，因此我们将将其用作占位符。
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
            //大多数Selenium会话中的大多数命令都是与元素相关的
            WebElement textBox = driver.findElement(By.name("my-text"));
            WebElement submitButton = driver.findElement(By.cssSelector("button"));
            //对一个元素只有少数操作，但您会经常使用它们。
            textBox.sendKeys("Selenium");
            submitButton.click();
            //元素存储了大量可以请求的信息。
            WebElement message = driver.findElement(By.id("message"));
            String value = message.getText();
            logger.debug("Received!{}", value);
            // 结束会话
        }finally {
            driver.quit();
        }
    }
}
