package unosquare.challenge.base;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import unosquare.challenge.driver.WebDriverSingleton;
import unosquare.challenge.pages.HomePage;
import unosquare.challenge.pages.SeleniumPage;
import unosquare.challenge.utils.JsonUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;
    protected SeleniumPage selenium;
    private final JsonUtils jsonUtils = new JsonUtils();

    @BeforeTest
    public void setUp() throws IOException, ParseException {

        JSONObject data = jsonUtils.parseJson();
        Object dataBrowser = data.get("browser");
        Object dataUrl = data.get("url");
        driver = SeleniumPage.getInstance().getDriver();
        driver.manage().window().maximize();
        driver.get((String) dataUrl);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
