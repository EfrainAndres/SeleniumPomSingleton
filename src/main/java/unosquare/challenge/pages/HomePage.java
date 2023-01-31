package unosquare.challenge.pages;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import unosquare.challenge.selenium.SeleniumBase;

import java.io.IOException;

public class HomePage {
    private static HomePage instance;
    private final SeleniumBase selenium;

    private HomePage() throws IOException, ParseException {
        selenium = SeleniumBase.getSeleniumBase();
    }

    public static HomePage getInstance() throws IOException, ParseException {
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
