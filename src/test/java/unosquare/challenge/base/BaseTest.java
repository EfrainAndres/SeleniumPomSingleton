package unosquare.challenge.base;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import unosquare.challenge.driver.WebDriverSingleton;
import unosquare.challenge.selenium.SeleniumBase;
import unosquare.challenge.utils.JsonUtils;

import java.io.IOException;

public class BaseTest {
    public WebDriver driver;
    public SeleniumBase selenium;
    private final JsonUtils jsonUtils = new JsonUtils();

    @BeforeTest
    public void setUp() throws IOException, ParseException {
        JSONObject data = jsonUtils.parseJson();
        String dataBrowser = (String) data.get("browser");
        String dataUrl = (String) data.get("url");
        driver = WebDriverSingleton.getInstance(dataBrowser).getDriver();
        selenium = SeleniumBase.getSeleniumBase();
        selenium.setDriver(driver);
        driver.manage().window().maximize();
        driver.get(dataUrl);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
