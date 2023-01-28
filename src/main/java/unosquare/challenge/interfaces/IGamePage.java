package unosquare.challenge.interfaces;

import org.openqa.selenium.WebDriver;

public interface IGamePage {
    void countPrintElements(WebDriver driver);
    void printItemTitles();
    String getFirstNonFreePrice();
    String getCurrentPrice();
    void addFirstItemToCart(WebDriver driver);
}
