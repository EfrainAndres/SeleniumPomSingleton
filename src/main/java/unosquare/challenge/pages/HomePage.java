package unosquare.challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    private static HomePage instance;
    private SeleniumPage selenium;

    private HomePage() {
        selenium = SeleniumPage.getInstance();
    }

    public static HomePage getInstance() {
        if (instance == null) {
            instance = new HomePage();
        }
        return instance;
    }
    private final By windowsButton = By.id("shellmenu_2");

    public void navigateToWindows() {
        selenium.waitForElementToBeClickable(windowsButton);
        selenium.click(windowsButton);
    }
}
