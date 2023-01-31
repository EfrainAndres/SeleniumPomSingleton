package unosquare.challenge.interfaces;

import org.openqa.selenium.WebDriver;

public interface IGamePage {
    void countPrintElements(WebDriver driver);
    String getFirstNonFreePrice();
    String getCurrentPrice();
    void addFirstItemToCart(WebDriver driver);
}
