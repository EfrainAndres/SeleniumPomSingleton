package unosquare.challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import unosquare.challenge.selenium.SeleniumBase;

import java.util.List;

public class ShoppingCartPage {
    private final SeleniumBase seleniumBase;

    public ShoppingCartPage(SeleniumBase seleniumBase) {
        this.seleniumBase = seleniumBase;
    }
    private final By orderDelete = By.xpath("//*/text()[normalize-space(.)='Remove']/parent::*");
    private final By emptyCart = By.xpath("//*/text()[normalize-space(.)='Your cart is empty.']/parent::*");

    public int getItemCount() {
        By by = By.xpath("//*[@id=\"store-cart-root\"]/div/div/div/section[1]/div");
        seleniumBase.waitForElementToBePresent(by);
        List<WebElement> hola = seleniumBase.findElementsBy(by);
        return hola.size();
    }

    public void deleteItem() {
        seleniumBase.waitForElementToBeClickable(orderDelete);
        seleniumBase.click(orderDelete);
    }

    public String isEmptyMessageDisplayed() {
        seleniumBase.waitForElementToBePresent(emptyCart);
        return seleniumBase.getText(emptyCart);
    }

    public void assertItemAccount() {
        seleniumBase.assertNumberIsEqual(getItemCount(), 1, "Correct number of items in the cart");
    }

    public void assertDisplayMessage() {
        seleniumBase.assertTextIsEqual(isEmptyMessageDisplayed(), "Your cart is empty.", "Your cart is empty.");
    }
}
