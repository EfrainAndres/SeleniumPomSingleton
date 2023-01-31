package unosquare.challenge.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {
    private static WebDriverSingleton webDriverSingleton;
    private static WebDriver driver;

    public static WebDriverSingleton getInstance(String browser) {
        if (browser.equals("chrome")){
            webDriverSingleton = new WebDriverSingleton();
            driver = new ChromeDriver();
        }else if (browser.equals("firefox")) {
            webDriverSingleton = new WebDriverSingleton();
            driver = new FirefoxDriver();
        }

        return webDriverSingleton;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
