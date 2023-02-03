package unosquare.challenge;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import unosquare.challenge.base.BaseTest;
import unosquare.challenge.pages.*;
import unosquare.challenge.selenium.SeleniumBase;
import unosquare.challenge.utils.JsonUtils;

import java.io.IOException;

public class SearchValidationTest extends BaseTest {
    private final JsonUtils jsonUtils = new JsonUtils();

    @Test
    public void searchValidationTest() throws IOException, ParseException {
        SeleniumBase seleniumBase;
        HomePage homePage = HomePage.getInstance();
        WindowsPage windowsPage = WindowsPage.getInstance();
        SearchPage searchPage = SearchPage.getInstance();
        GamesPage gamesPage = GamesPage.getInstance();
        ShoppingCartPage shoppingCartPage = ShoppingCartPage.getInstance();
        JSONObject data = jsonUtils.parseJson();

        // 2. Go to Windows
        homePage.navigateToWindows();

        // 3. Go to Search
        windowsPage.navigateToSearch();

        // 4. Search for “Xbox” and click on "Comprar"
        searchPage.search((String) data.get("search"));
        searchPage.BuyButton();

        // 5. Once in the result page you will see "Aplicaciones" click on it
        searchPage.navigateToGames();

        // 6. Count the elements on the first 3 pages and print the sum of elements and all the titles
        gamesPage.countPrintElements();
        gamesPage.paginateFirstPage();
        gamesPage.clickButtonPriceFilter();
        gamesPage.clickPriceFilter();

        // 7. Go back to the first page and select the first NON-FREE option and STORE the price for later comparison
        String firstPrice = gamesPage.getFirstNonFreePrice();
        gamesPage.addFirstItemToCart();

        // 8. If you see a "Registration" pop up, close it
        gamesPage.closeRegistrationPopUp();

        // 9. Click on the "3 dot" button next to "Comprar" button and add the item to the CART
        gamesPage.addToCart();

        // 10. In this page, you will see the price again, compare first price vs current prince and they should match
        gamesPage.assertValues();
        // 11. Verify the app takes you to the Shopping Cart page and verify you have one element available
        shoppingCartPage.assertItemAccount();

        // 12. Delete the item and verify you have 0 elements: "Tu carro está vacío" message should be present
        shoppingCartPage.deleteItem();
        shoppingCartPage.assertDisplayMessage();
    }
}