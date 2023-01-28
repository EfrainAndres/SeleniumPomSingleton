package unosquare.challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import unosquare.challenge.driver.WebDriverSingleton;

import java.time.Duration;
import java.util.List;

public class SeleniumPage {
    private static WebDriver driver;
    private static SeleniumPage instance;

    public static SeleniumPage getInstance() {
        if (instance == null) {
            instance = new SeleniumPage();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = WebDriverSingleton.getInstance().getDriver();
        }
        return driver;
    }

    public WebElement findElementBy(By by) {
        return driver.findElement(by);
    }

    public List<WebElement> findElementsBy(By by) {
        return driver.findElements(by);
    }

    public void click(By element) {
        //element.click();
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

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
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
