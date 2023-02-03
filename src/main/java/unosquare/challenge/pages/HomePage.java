package unosquare.challenge.pages;

import org.openqa.selenium.By;
import unosquare.challenge.selenium.SeleniumBase;

public class HomePage {
    private static HomePage homePage;

    private final SeleniumBase selenium;

    private HomePage() {
        selenium = new SeleniumBase();
    }

    public static HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    private final By windowsButton = By.id("shellmenu_2");

    public void navigateToWindows() {
        selenium.waitForElementToBeClickable(windowsButton);
        selenium.click(windowsButton);
    }
}
