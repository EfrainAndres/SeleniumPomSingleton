package unosquare.challenge.pages;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import unosquare.challenge.selenium.SeleniumBase;

import java.io.IOException;

public class WindowsPage {

    private static WindowsPage instance;
    private final SeleniumBase seleniumPage;

    private WindowsPage() throws IOException, ParseException {
        seleniumPage = SeleniumBase.getSeleniumBase();
    }

    public static WindowsPage getInstance() throws IOException, ParseException {
        if (instance == null) {
            instance = new WindowsPage();
        }
        return instance;
    }

    private final By searchButton = By.id("search");

    public void navigateToSearch() {
        seleniumPage.waitForElementToBeClickable(searchButton);
        seleniumPage.click(searchButton);
    }
}
