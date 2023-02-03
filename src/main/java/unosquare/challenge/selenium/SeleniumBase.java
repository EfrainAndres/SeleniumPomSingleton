package unosquare.challenge.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.time.Duration;
import java.util.List;

public class SeleniumBase {
    private static WebDriver driver;
    private static SeleniumBase selenium;

    public static SeleniumBase getInstance(String browser) {

        switch (browser) {
            case "chrome":
                selenium = new SeleniumBase();
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                selenium = new SeleniumBase();
                break;
            case "edge":
                driver = new EdgeDriver();
                selenium = new SeleniumBase();
                break;
            default:
                Reporter.log("Invalid browser type");
                break;
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBePresent(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
