package unosquare.challenge.pages;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import unosquare.challenge.selenium.SeleniumBase;

import java.io.IOException;
import java.util.List;

public class ShoppingCartPage {
    private static ShoppingCartPage instance;
    private final SeleniumBase seleniumPage;

    private ShoppingCartPage() throws IOException, ParseException {
        seleniumPage = SeleniumBase.getSeleniumBase();
    }

    public static ShoppingCartPage getInstance() throws IOException, ParseException {
        if (instance == null) {
            instance = new ShoppingCartPage();
        }
        return instance;
    }
    private final By orderDelete = By.xpath("//*/text()[normalize-space(.)='Remove']/parent::*");
    private final By emptyCart = By.xpath("//*/text()[normalize-space(.)='Your cart is empty.']/parent::*");

    public int getItemCount() {
        By by = By.xpath("//*[@id=\"store-cart-root\"]/div/div/div/section[1]/div");
        seleniumPage.waitForElementToBePresent(by);
        List<WebElement> hola = seleniumPage.findElementsBy(by);
        return hola.size();
    }

    public void deleteItem() {
        seleniumPage.waitForElementToBeClickable(orderDelete);
        seleniumPage.click(orderDelete);
    }

    public String isEmptyMessageDisplayed() {
        seleniumPage.waitForElementToBePresent(emptyCart);
        return seleniumPage.getText(emptyCart);
    }

    public void assertItemAccount() {
        seleniumPage.assertNumberIsEqual(getItemCount(), 1, "Correct number of items in the cart");
    }

    public void assertDisplayMessage() {
        seleniumPage.assertTextIsEqual(isEmptyMessageDisplayed(), "Your cart is empty.", "Your cart is empty.");
    }
}
