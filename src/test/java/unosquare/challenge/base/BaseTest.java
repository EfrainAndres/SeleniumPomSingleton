package unosquare.challenge.base;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import unosquare.challenge.selenium.SeleniumBase;
import unosquare.challenge.utils.JsonUtils;

import java.io.IOException;

public class BaseTest {
    protected SeleniumBase seleniumBase;
    private final JsonUtils jsonUtils = new JsonUtils();

    @BeforeTest
    public void setUp() throws IOException, ParseException {
        JSONObject data = jsonUtils.parseJson();
        String dataBrowser = (String) data.get("browser");
        String dataUrl = (String) data.get("url");
        seleniumBase = SeleniumBase.getInstance(dataBrowser);
        seleniumBase.getDriver().get(dataUrl);
        seleniumBase.getDriver().manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        seleniumBase.getDriver().quit();
    }
}
