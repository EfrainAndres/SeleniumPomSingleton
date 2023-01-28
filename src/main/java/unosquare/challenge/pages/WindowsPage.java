package unosquare.challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import unosquare.challenge.base.BaseTest;

public class WindowsPage extends BaseTest {

    private static WindowsPage instance;
    private SeleniumPage seleniumPage;

    private WindowsPage() {
        seleniumPage = SeleniumPage.getInstance();
    }

    public static WindowsPage getInstance() {
        if (instance == null) {
            instance = new WindowsPage();
        }
        return instance;
    }

    private final By searchButton = By.id("search");

    public void navigateToSearch() throws InterruptedException {
        seleniumPage.waitForElementToBeClickable(searchButton);
        seleniumPage.click(searchButton);
    }
}
