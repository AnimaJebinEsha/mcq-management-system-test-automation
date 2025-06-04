package tech.innospace.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import tech.innospace.utils.WebDriverFactory;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
