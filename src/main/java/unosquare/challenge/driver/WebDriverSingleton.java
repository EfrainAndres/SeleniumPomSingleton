package unosquare.challenge.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {
    private static WebDriverSingleton instance;
    private static WebDriver driver;

    private WebDriverSingleton() {
        // private constructor to prevent instantiation
    }

    public static WebDriverSingleton getInstance() {
        if (instance == null) {
            instance = new WebDriverSingleton();
            driver = new ChromeDriver();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
