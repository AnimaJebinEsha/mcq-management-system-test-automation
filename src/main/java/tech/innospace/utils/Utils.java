package tech.innospace.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class Utils {

    private Utils() {
        throw new AssertionError("Not instantiable!");
    }

    public static void clickDropdownAndSelectItem(By dropdownWrapper, String itemLabel, WebDriver driver) {
        WebElement dropdown = WaitUtils.waitForElementVisible(driver, dropdownWrapper);
        dropdown.click();
        dropdown.clear();
        dropdown.sendKeys(itemLabel);

        if ("AutomationChapter1".equals(itemLabel)) {
            itemLabel = "1 " + itemLabel;
        }

        if ("AutomationTopic1".equals(itemLabel)) {
            By itemLocator = By.xpath("//p[normalize-space(text())='" + itemLabel + "']");
            WebElement itemTextElement = WaitUtils.waitForElementVisible(driver, itemLocator);
            WebElement clickableItemElement = itemTextElement.findElement(By.xpath("../../.."));
            clickableItemElement.click();
        } else {
            By itemLocator = By.xpath("//div[@title='" + itemLabel + "']");
            WaitUtils.waitForElementVisible(driver, itemLocator);
            WaitUtils.waitForClickable(driver, itemLocator);
            driver.findElement(itemLocator).click();
        }
    }
}
