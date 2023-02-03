package unosquare.challenge.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import unosquare.challenge.utils.JsonUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SeleniumBase {
    private static WebDriver driver;
    private static SeleniumBase selenium;

    public static SeleniumBase getInstance () throws IOException, ParseException {
        JsonUtils jsonUtils = new JsonUtils();
        JSONObject data = jsonUtils.parseJson();
        String dataBrowser = (String) data.get("browser");
        if (dataBrowser.equals("chrome")) {
            selenium = new SeleniumBase();
            driver = new ChromeDriver();
        } else if (dataBrowser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            selenium = new SeleniumBase();
        }

        return selenium;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement findElementBy(By by) {
        return driver.findElement(by);
    }

    public List<WebElement> findElementsBy(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements;
    }

    public void click(By element) {
        driver.findElement(element).click();
    }

    public void sendKeys(By element, String keys) {
        driver.findElement(element).sendKeys(keys);
    }

    public String getText(By element) {
        return driver.findElement(element).getText();
    }

    public int getSize(By element) {
        return driver.findElements(element).size();
    }

    public String getAttribute(By element, String attribute) {
        return driver.findElement(element).getAttribute(attribute);
    }

    public void waitForElementToBeClickable(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBePresent(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void waitForElementsToBePresent(By elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elements));
    }

    public void assertElementIsDisplayed(By locator) {
        Assert.assertTrue(driver.findElement(locator).isDisplayed());
    }

    public void assertElementIsEnabled(By locator) {
        Assert.assertTrue(driver.findElement(locator).isEnabled());
    }

    public void assertTextIsEqual(String actualText, String expectedText, String message) {
        Assert.assertEquals(actualText, expectedText, message);
    }

    public void assertNumberIsEqual(int actualValue, int expectedValue, String message) {
        Assert.assertEquals(actualValue, expectedValue, message);
    }
}
