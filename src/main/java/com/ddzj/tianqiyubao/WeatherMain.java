package com.ddzj.tianqiyubao;

import com.ddzj.proxyip.IpMain;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WeatherMain {
    private static Logger logger = LoggerFactory.getLogger(WeatherMain.class);

    /**
     * 中国天气官网数据
     */
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/zz/Documents/tools/usetools/selenium/chromedriver");

        //页面加载策略
        //标准	完整	默认使用，等待所有资源下载
        String str = IpMain.getPorxy();
        ChromeOptions chromeOptions = new ChromeOptions();
        // 设置代理
        //chromeOptions.addArguments("--proxy-server=" + str);
        // 设置等待网站加载完成
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        // 设置header参数
        chromeOptions.addArguments("Accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        chromeOptions.addArguments("Accept-Encoding=gzip, deflate");
        chromeOptions.addArguments("Accept-Language=zh-CN,zh;q=0.9");
        chromeOptions.addArguments("Cache-Control=no-cache");
        chromeOptions.addArguments("Host=www.weather.com.cn");
        chromeOptions.addArguments("Pragma=no-cache");
        chromeOptions.addArguments("Proxy-Connection=keep-alive");
        chromeOptions.addArguments("Upgrade-Insecure-Requests=1");
        chromeOptions.addArguments("User-Agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

        WebDriver driver = new ChromeDriver(chromeOptions);
        try {
            driver.get("http://www.weather.com.cn/weather1d/101250405.shtml");
            //您可以请求许多关于浏览器的信息类型，包括窗口句柄、浏览器大小/位置、cookie、警报等。
            String title = driver.getTitle();
            logger.debug("Web form:{}", title);
            // 隐含的等待
            //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
            // 显式等待
            WebElement revealed = driver.findElement(By.cssSelector(".mySkyNull"));
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(d -> revealed.isDisplayed());
            WebElement submitButton = driver.findElement(By.cssSelector(".mySkyNull .tem"));
            System.out.println(submitButton.getText());

            // 结束会话
        } finally {
            driver.quit();
        }
    }
}
