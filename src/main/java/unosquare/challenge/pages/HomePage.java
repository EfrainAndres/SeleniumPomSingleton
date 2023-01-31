package unosquare.challenge.pages;

import org.openqa.selenium.By;
import unosquare.challenge.selenium.SeleniumBase;

public class HomePage {
    private final SeleniumBase seleniumBase;

    public HomePage(SeleniumBase seleniumBase) {
        this.seleniumBase = seleniumBase;
    }
    private final By windowsButton = By.id("shellmenu_2");

    public void navigateToWindows() {
        seleniumBase.waitForElementToBeClickable(windowsButton);
        seleniumBase.click(windowsButton);
    }
}
