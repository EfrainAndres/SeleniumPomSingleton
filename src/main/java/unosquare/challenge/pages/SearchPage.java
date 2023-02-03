package unosquare.challenge.pages;

import org.openqa.selenium.By;
import unosquare.challenge.selenium.SeleniumBase;

public class SearchPage {
    private static SearchPage searchPage;
    private final SeleniumBase seleniumPage;

    private SearchPage() {
        seleniumPage = new SeleniumBase();
    }

    public static SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }

    private final By searchInput = By.id("cli_shellHeaderSearchInput");
    private final By buyButton = By.linkText("Shop");
    private final By searchButton = By.id("search");
    private final By gamesButton = By.linkText("Games");

    public void search(String keyword) {
        seleniumPage.sendKeys(searchInput, keyword);
        seleniumPage.waitForElementToBeClickable(searchButton);
        seleniumPage.click(searchButton);
    }

    public void BuyButton() {
        seleniumPage.waitForElementToBeClickable(buyButton);
        seleniumPage.click(buyButton);
    }

    public void navigateToGames() {
        seleniumPage.waitForElementToBeClickable(gamesButton);
        seleniumPage.click(gamesButton);
    }
}
