package unosquare.challenge.pages;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import unosquare.challenge.interfaces.IGamePage;
import unosquare.challenge.selenium.SeleniumBase;
import unosquare.challenge.utils.FunctionUtils;

import java.io.IOException;
import java.util.List;

public class GamesPage implements IGamePage {

    private static GamesPage instance;
    private final SeleniumBase seleniumPage;

    private GamesPage() throws IOException, ParseException {
        seleniumPage = new SeleniumBase();
    }

    public static GamesPage getInstance() throws IOException, ParseException {
        if (instance == null) {
            instance = new GamesPage();
        }
        return instance;
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
        seleniumPage.waitForElementToBeClickable(firstPage);
        seleniumPage.click(firstPage);
    }

    public void clickButtonPriceFilter() {
        seleniumPage.waitForElementToBeClickable(buttonPriceFilter);
        seleniumPage.click(buttonPriceFilter);
    }

    public void clickPriceFilter() {
        seleniumPage.waitForElementToBeClickable(priceFilter);
        seleniumPage.click(priceFilter);
    }

    @Override
    public void countPrintElements() {
        for (int i = 1; i <= 3; i++) {
            By allGamesPage = By.xpath("//*[@id='shopDetailsWrapper']/div[1]/div[3]/div/ul/li/div");
            List<WebElement> titlesList = seleniumPage.findElementsBy(allGamesPage);
            By detailsAllElements = By.id("shopDetailsWrapper");

            seleniumPage.waitForElementToBePresent(detailsAllElements);
            seleniumPage.waitForElementToBeClickable(By.xpath("//ul[@class='pagination']/li[" + i + "]"));
            elementCount += titlesList.size();
            for (WebElement title : titlesList) {
                System.out.println("Titles page " + i + ": " + title.getText());
            }

            if (i < 3) {
                seleniumPage.waitForElementToBeClickable(nextPage);
                seleniumPage.click(nextPage);
            }
        }
        System.out.println("Total elements: " + elementCount);
    }

    public WebElement clickProduct(String elementName) {
        seleniumPage.waitForElementToBeClickable(By.id(elementName));
        return seleniumPage.findElementBy(By.id(elementName));
    }

    public String getFirstNonFreePrice() {
        By allGamesPage = By.xpath("//*[@id='shopDetailsWrapper']/div[1]/div[3]/div/ul/li/div");

        List<WebElement> titlesList = seleniumPage.findElementsBy(allGamesPage);
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
    public void addFirstItemToCart() {
        seleniumPage.waitForElementToBeClickable(By.id(productID));
        clickProduct(productID).click();
    }

    public void closeRegistrationPopUp() {
        seleniumPage.waitForElementToBeClickable(popUpClose);
        seleniumPage.click(popUpClose);
    }

    public void addToCart() {
        seleniumPage.waitForElementToBeClickable(buyGameButton);
        seleniumPage.click(buyGameButton);
        seleniumPage.waitForElementToBeClickable(addToCart);
        seleniumPage.click(addToCart);
    }

    public void assertValues() {
        seleniumPage.assertTextIsEqual("$" + firstPrice, getCurrentPrice(), "Prices match");
    }
}
