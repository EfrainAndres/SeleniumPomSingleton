package unosquare.challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import unosquare.challenge.interfaces.IGamePage;
import unosquare.challenge.utils.FunctionUtils;

import java.util.List;

public class GamesPage implements IGamePage {

    private static GamesPage instance;
    private SeleniumPage seleniumPage;

    private GamesPage() {
        seleniumPage = SeleniumPage.getInstance();
    }

    public static GamesPage getInstance() {
        if (instance == null) {
            instance = new GamesPage();
        }
        return instance;
    }

    private String firstPrice;
    private String productID;
    private int elementCount = 0;
    private final FunctionUtils functionUtils = new FunctionUtils();
    private final By firstPage = By.id("//ul[@class='pagination']/li[1]");
    By by = By.xpath("//*[@id='shopDetailsWrapper']/div[1]/div[3]/div/ul/li/div");
    private final List<WebElement> itemTitles = seleniumPage.findElementsBy(by);
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
    public void countPrintElements(WebDriver driver) {
        for (int i = 1; i <= 3; i++) {
            driver.findElement(By.xpath("//ul[@class='pagination']/li[" + i +"]")).click();

            elementCount += itemTitles.size();

            for (WebElement title : itemTitles) {
                System.out.println("Titles page " + i + ": " + title.getText());
            }
        }
        System.out.println("Total number of elements: " + elementCount);
    }

    @Override
    public void printItemTitles() {
        for (WebElement title : itemTitles) {
            System.out.println("Title: " + title.getText());
        }
    }

    public WebElement clickProduct(WebDriver driver, String elementName) {
        return driver.findElement(By.id(elementName));
    }

    public String getFirstNonFreePrice() {
        for (WebElement price : itemTitles) {
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
        seleniumPage.waitForElementToBeClickable((By) clickProduct(driver,productID));
        clickProduct(driver,productID).click();
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
}
