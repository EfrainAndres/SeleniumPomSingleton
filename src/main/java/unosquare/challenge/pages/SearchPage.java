package unosquare.challenge.pages;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import unosquare.challenge.selenium.SeleniumBase;

import java.io.IOException;

public class SearchPage {
    private static SearchPage instance;
    private SeleniumBase seleniumPage;

    private SearchPage() throws IOException, ParseException {
        seleniumPage = new SeleniumBase();
    }

    public static SearchPage getInstance() throws IOException, ParseException {
        if (instance == null) {
            instance = new SearchPage();
        }
        return instance;
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
