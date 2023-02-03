package unosquare.challenge.interfaces;

import org.openqa.selenium.WebDriver;

public interface IGamePage {
    void countPrintElements();
    String getFirstNonFreePrice();
    String getCurrentPrice();
    void addFirstItemToCart();
}
