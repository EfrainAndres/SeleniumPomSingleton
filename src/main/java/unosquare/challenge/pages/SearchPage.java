package unosquare.challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import unosquare.challenge.base.BaseTest;

public class SearchPage extends BaseTest {
    private static SearchPage instance;
    private SeleniumPage seleniumPage;

    private SearchPage() {
        seleniumPage = SeleniumPage.getInstance();
    }

    public static SearchPage getInstance() {
        if (instance == null) {
            instance = new SearchPage();
        }
        return instance;
    }

    private final By searchInput = By.id("cli_shellHeaderSearchInput");
    private final By buyButton = By.linkText("Shop");
    private final By searchButton = By.id("search");
    private final By gamesButton = By.linkText("Games");

    public void search(String keyword) throws InterruptedException {
        seleniumPage.sendKeys(searchInput, keyword);
        seleniumPage.waitForElementToBeClickable(searchButton);
        seleniumPage.click(searchButton);
    }

    public void BuyButton() throws InterruptedException {
        seleniumPage.waitForElementToBeClickable(buyButton);
        seleniumPage.click(buyButton);
    }

    public void navigateToGames() {
        seleniumPage.waitForElementToBeClickable(gamesButton);
        seleniumPage.click(gamesButton);
    }
}
