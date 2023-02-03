package unosquare.challenge.pages;

import org.openqa.selenium.By;
import unosquare.challenge.selenium.SeleniumBase;

public class WindowsPage {

    private static WindowsPage windowsPage;
    private final SeleniumBase seleniumPage;

    private WindowsPage() {
        seleniumPage = new SeleniumBase();
    }

    public static WindowsPage getWindowsPage() {
        if (windowsPage == null) {
            windowsPage = new WindowsPage();
        }
        return windowsPage;
    }

    private final By searchButton = By.id("search");

    public void navigateToSearch() {
        seleniumPage.waitForElementToBeClickable(searchButton);
        seleniumPage.click(searchButton);
    }
}
