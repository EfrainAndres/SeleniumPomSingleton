package unosquare.challenge.pages;

import org.openqa.selenium.By;
import unosquare.challenge.selenium.SeleniumBase;

public class WindowsPage {

    private final SeleniumBase seleniumBase;

    public WindowsPage(SeleniumBase seleniumBase) {
        this.seleniumBase = seleniumBase;
    }

    private final By searchButton = By.id("search");

    public void navigateToSearch() {
        seleniumBase.waitForElementToBeClickable(searchButton);
        seleniumBase.click(searchButton);
    }
}
