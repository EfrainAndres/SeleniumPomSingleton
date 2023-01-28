package unosquare.challenge;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SampleTest {

    private WebDriver driver;
    //private Data data;
    private String titleProduct;
    private String firstPrice;

    // Page Factory
    @FindBy(xpath = "//*[@id='shellmenu_2']")
    private WebElement windowsButton;

    @FindBy(id = "search")
    private WebElement searchButton;

    @FindBy(id = "cli_shellHeaderSearchInput")
    private WebElement searchInput;

    @FindBy(linkText = "Shop")
    private  WebElement buyButton;

    @FindBy(linkText = "Games")
    private WebElement gamesButton;

    @FindBy(xpath = "//ul[@class='pagination']/li[1]")
    private WebElement firstPage;

    @FindBy(xpath = "//ul[@class='pagination']/li[2]")
    private WebElement secondPage;

    @FindBy(xpath = "//ul[@class='pagination']/li[3]")
    private WebElement thirdPage;

    @FindBy(xpath = "//*[@id='shopDetailsWrapper']/div[1]/div[3]/div/ul/li/div")
    private List<WebElement> itemTitles;

    @FindBy(xpath = "//span[contains(.,'$')]")
    private List<WebElement> itemPrices;

    @FindBy(xpath = "//*[@id='accordionProductFilters']/li[4]/h4/button")
    private WebElement buttonPriceFilter;

    @FindBy(xpath = "//*[@id='mwfPrice']/div[2]/a")
    private WebElement priceFilter;

    @FindBy(id = "R1MarketRedirect-1")
    private WebElement popUp;
    @FindBy(xpath = "//*[@id='R1MarketRedirect-1']/button")
    private WebElement popUpClose;

    @FindBy(id = "ButtonPanel_buttonPanel_OverflowMenuTrigger")
    private WebElement buyGameButton;

    @FindBy(id = "buttonPanel_AddToCartButton")
    private WebElement addToCart;

    @FindBy(xpath= "//*[@id='store-cart-root']/div/div/div/section[1]/div/div/div/div/div/div[2]/div[1]/div[2]/div/div/span/span[2]/span")
    private WebElement orderPrice;

    @FindBy(xpath = "//*[@id=\"store-cart-root\"]/div/div/div/section[1]/div/div/div/div/div/div[2]/div[1]/div[1]/h2/a")
    private WebElement orderTitle;

    @FindBy(xpath = "//*/text()[normalize-space(.)='Remove']/parent::*")
    private WebElement orderDelete;

    @FindBy(xpath = "//*[@id=\"store-cart-root\"]/div/div/div/section[1]/div")
    private List<WebElement> shoppingCartCount;

    @FindBy(xpath = "//*/text()[normalize-space(.)='Your cart is empty.']/parent::*")
    private WebElement emptyCart;

    @BeforeMethod
    public void setUp() throws IOException {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Initialize Page Factory
        PageFactory.initElements(driver, this);

        //Read Data from JSON
        //data = new ObjectMapper().readValue(new File("data.json"), Data.class);
    }

    @Test
    public void searchTest() {
        // 1. Go to https://www.microsoft.com/es-mx/
        driver.get("https://www.microsoft.com/en-us/");

        // 2. Go to Windows
        windowsButton.click();

        // 3. Go to Search
        searchButton.click();

        // 4. Search for “Xbox” and click on "Comprar"
        searchInput.sendKeys("Xbox");
        searchButton.click();

        buyButton.click();

        // 5. Once in the result page you will see "Aplicaciones" click on it
        gamesButton.click();

        // 6. Count the elements on the first 3 pages and print the sum of elements and all the titles
        /*int elementCountFirstPage = itemTitles.size();
        System.out.println("Number of elements page 1: " + elementCountFirstPage);

        for (WebElement title : itemTitles) {
            System.out.println("Titles page 1: " + title.getText());
        }

        secondPage.click();

        int elementCountSecondPage = itemTitles.size();
        System.out.println("Number of elements page 2: " + elementCountSecondPage);

        for (WebElement title : itemTitles) {
            System.out.println("Titles page 2: " + title.getText());
        }

        thirdPage.click();

        int elementCountThirdPage = itemTitles.size();
        System.out.println("Number of elements page 3: " + elementCountThirdPage);*/

        int elementCount = 0;
        for (int i = 1; i <= 3; i++) {
            // Navigate to the page
            //driver.get("https://example.com/page" + i);
            driver.findElement(By.xpath("//ul[@class='pagination']/li[" + i +"]")).click();

            // Find all elements on the page
            elementCount += itemTitles.size();

            // Print the title of the page
            for (WebElement title : itemTitles) {
                System.out.println("Titles page" + i + ": " + title.getText());
            }
        }
        // Print the total number of elements found
        System.out.println("Total number of elements: " + elementCount);

        firstPage.click();

        // 7. Go back to the first page and select the first NON-FREE option and STORE the price for later comparison

        buttonPriceFilter.click();
        priceFilter.click();

        for (WebElement price : itemTitles) {

            if (!price.getText().contains("Save") & !price.getText().contains("Game Pass")) {
                System.out.println("Title: " + price.getAttribute("id"));
                String priceSplit = price.getText();
                String[] parts = priceSplit.split("\\$");
                System.out.println(parts[2]);

                titleProduct = parts[0];
                firstPrice = parts[2];

                driver.findElement(By.id(price.getAttribute("id"))).click();

                break;
            }
        }

        // 8. If you see a "Registration" pop up, close it
        // Add code to close the registration pop-up here
        if (popUp.isDisplayed()) {
            popUpClose.click();
        }

        // 9. Click on the "3 dot" button next to "Comprar" button and add the item to the CART

        buyGameButton.click();
        addToCart.click();

        // 10. In this page, you will see the price again, compare first price vs current prince and they should match
        Assert.assertEquals("$" + firstPrice, orderPrice.getText(), "Prices match");

        // 11. Verify the app takes you to the Shopping Cart page and verify you have one element available
        Assert.assertEquals(titleProduct.trim(), orderTitle.getText(), "Correct number of items in the cart");
        Assert.assertEquals(shoppingCartCount.size(), 1, "Item was created");

        // 12. Delete the item and verify you have 0 elements: "Tu carro está vacío" message should be present
        orderDelete.click();
        Assert.assertEquals(shoppingCartCount.size(), 0, "Item was deleted");
        Assert.assertEquals(emptyCart.getText(), "Your cart is empty.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
