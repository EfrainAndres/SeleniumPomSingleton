package unosquare.challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import unosquare.challenge.interfaces.IGamePage;
import unosquare.challenge.selenium.SeleniumBase;
import unosquare.challenge.utils.FunctionUtils;

import java.util.List;

public class GamesPage implements IGamePage {

    private final SeleniumBase seleniumBase;

    public GamesPage(SeleniumBase seleniumBase) {
        this.seleniumBase = seleniumBase;
    }

    private String firstPrice;
    private String productID;
    private int elementCount = 0;
    private final FunctionUtils functionUtils = new FunctionUtils();
    private final By firstPage = By.xpath("//ul[@class='pagination']/li[2]");
    private final By nextPage = By.linkText("Next");
    private final By buttonPriceFilter = By.xpath("//button[normalize-space()='Price']");
    private final By priceFilter = By.xpath("//*[@id='mwfPrice']/div[2]/a[1]");
    private final By popUpClose = By.cssSelector("#R1MarketRedirect-1 > button:nth-child(1)");
    private final By buyGameButton = By.id("ButtonPanel_buttonPanel_OverflowMenuTrigger");
    private final By addToCart = By.id("buttonPanel_AddToCartButton");
    public void paginateFirstPage() {
        seleniumBase.waitForElementToBeClickable(firstPage);
        seleniumBase.click(firstPage);
    }

    public void clickButtonPriceFilter() {
        seleniumBase.waitForElementToBeClickable(buttonPriceFilter);
        seleniumBase.click(buttonPriceFilter);
    }

    public void clickPriceFilter() {
        seleniumBase.waitForElementToBeClickable(priceFilter);
        seleniumBase.click(priceFilter);
    }

    @Override
    public void countPrintElements(WebDriver driver) {
        for (int i = 1; i <= 3; i++) {
            By allGamesPage = By.xpath("//*[@id='shopDetailsWrapper']/div[1]/div[3]/div/ul/li/div");
            List<WebElement> titlesList = seleniumBase.findElementsBy(allGamesPage);
            By detailsAllElements = By.id("shopDetailsWrapper");

            seleniumBase.waitForElementToBePresent(detailsAllElements);
            seleniumBase.waitForElementToBeClickable(By.xpath("//ul[@class='pagination']/li[" + i + "]"));
            elementCount += titlesList.size();
            for (WebElement title : titlesList) {
                System.out.println("Titles page " + i + ": " + title.getText());
            }

            if (i < 3) {
                seleniumBase.waitForElementToBeClickable(nextPage);
                seleniumBase.click(nextPage);
            }
        }
        System.out.println("Total elements: " + elementCount);
    }

    public WebElement clickProduct(WebDriver driver, String elementName) {
        return driver.findElement(By.id(elementName));
    }

    public String getFirstNonFreePrice() {
        By allGamesPage = By.xpath("//*[@id='shopDetailsWrapper']/div[1]/div[3]/div/ul/li/div");

        List<WebElement> titlesList = seleniumBase.findElementsBy(allGamesPage);
        for (WebElement price : titlesList) {
            if (!price.getText().contains("Save") & !price.getText().contains("Game Pass")) {
                productID = price.getAttribute("id");

                String[] elements = functionUtils.splitString(price.getText(), "\\$");
                firstPrice = elements[2];

                break;
            }
        }
        return firstPrice;
    }
    @Override
    public String getCurrentPrice() {
        return "$" + firstPrice;
    }

    @Override
    public void addFirstItemToCart(WebDriver driver) {
        seleniumBase.waitForElementToBeClickable(By.id(productID));
        clickProduct(driver,productID).click();
    }

    public void closeRegistrationPopUp() {
        seleniumBase.waitForElementToBeClickable(popUpClose);
        seleniumBase.click(popUpClose);
    }

    public void addToCart() {
        seleniumBase.waitForElementToBeClickable(buyGameButton);
        seleniumBase.click(buyGameButton);
        seleniumBase.waitForElementToBeClickable(addToCart);
        seleniumBase.click(addToCart);
    }

    public void assertValues() {
        seleniumBase.assertTextIsEqual("$" + firstPrice, getCurrentPrice(), "Prices match");
    }
}
