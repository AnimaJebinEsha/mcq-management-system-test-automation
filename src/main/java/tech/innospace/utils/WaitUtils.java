package tech.innospace.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {

    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private WaitUtils() {
        throw new AssertionError("Not instantiable!");
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean waitForUrlContains(WebDriver driver, String partialUrl) {
        return new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.urlContains(partialUrl));
    }

    public static void waitForClickable(WebDriver driver, By locator) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted during wait", e);
        }
    }
}
