package tech.innospace.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class WebDriverFactory {

    private WebDriverFactory() {
        throw new AssertionError("Not instantiable!");
    }

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
