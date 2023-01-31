package unosquare.challenge.pages;

import org.openqa.selenium.By;
import unosquare.challenge.selenium.SeleniumBase;

public class SearchPage {
    private final SeleniumBase seleniumBase;

    public SearchPage(SeleniumBase seleniumBase) {
        this.seleniumBase = seleniumBase;
    }

    private final By searchInput = By.id("cli_shellHeaderSearchInput");
    private final By buyButton = By.linkText("Shop");
    private final By searchButton = By.id("search");
    private final By gamesButton = By.linkText("Games");

    public void search(String keyword) {
        seleniumBase.sendKeys(searchInput, keyword);
        seleniumBase.waitForElementToBeClickable(searchButton);
        seleniumBase.click(searchButton);
    }

    public void BuyButton() {
        seleniumBase.waitForElementToBeClickable(buyButton);
        seleniumBase.click(buyButton);
    }

    public void navigateToGames() {
        seleniumBase.waitForElementToBeClickable(gamesButton);
        seleniumBase.click(gamesButton);
    }
}
