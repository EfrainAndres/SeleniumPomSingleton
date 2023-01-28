package unosquare.challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import unosquare.challenge.base.BaseTest;

import java.util.List;

public class ShoppingCartPage extends BaseTest {
    private static ShoppingCartPage instance;
    private SeleniumPage seleniumPage;

    private ShoppingCartPage() {
        seleniumPage = SeleniumPage.getInstance();
    }

    public static ShoppingCartPage getInstance() {
        if (instance == null) {
            instance = new ShoppingCartPage();
        }
        return instance;
    }
    private final List<By> shoppingCartCount = (List<By>) By.xpath("//*[@id=\"store-cart-root\"]/div/div/div/section[1]/div");
    private final By orderDelete = By.xpath("//*/text()[normalize-space(.)='Remove']/parent::*");
    private final By emptyCart = By.xpath("//*/text()[normalize-space(.)='Your cart is empty.']/parent::*");

    public int getItemCount() {
        seleniumPage.waitForElementsToBePresent((By) shoppingCartCount);
        return shoppingCartCount.size();
    }

    public void deleteItem() {
        seleniumPage.waitForElementToBeClickable(orderDelete);
        seleniumPage.click(orderDelete);
    }

    public String isEmptyMessageDisplayed() {
        seleniumPage.waitForElementToBePresent(emptyCart);
        return seleniumPage.getText(emptyCart);
    }
}
