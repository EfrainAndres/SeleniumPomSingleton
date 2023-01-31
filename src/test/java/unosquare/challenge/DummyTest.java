package unosquare.challenge;

import org.testng.annotations.Test;
import unosquare.challenge.base.BaseTest;

public class DummyTest extends BaseTest {
    @Test
    public void searchTest() {
        // 1. Go to https://www.microsoft.com/en-us/
        driver.get("https://www.microsoft.com/en-us/");
    }
}
