package tech.innospace.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import tech.innospace.utils.WebDriverFactory;

public abstract class BaseTest {

    protected static WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        driver = WebDriverFactory.createDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDownAll() {
        if (driver != null) {
            driver.quit();
        }
    }
}
