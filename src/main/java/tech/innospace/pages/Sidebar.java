package tech.innospace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tech.innospace.utils.WaitUtils;

public class Sidebar {

    private final WebDriver driver;

    private final By mcqDropdown = By.xpath("//div[@role='menuitem' and .//span[text()='MCQ']]");
    private final By listMcqLink = By.xpath("//a[@href='/dashboard/mcq']");
    private final By mcqCreateLink = By.xpath("//a[@href='/dashboard/mcq/create']");

    public Sidebar(WebDriver driver) {
        this.driver = driver;
    }

    public void openMcqDropdown() {
        WaitUtils.waitForClickable(driver, mcqDropdown);
        driver.findElement(mcqDropdown).click();
    }

    public void clickListMcqLink() {
        WaitUtils.waitForClickable(driver, listMcqLink);
        driver.findElement(listMcqLink).click();
    }

    public void clickMcqCreateLink() {
        WaitUtils.waitForClickable(driver, mcqCreateLink);
        driver.findElement(mcqCreateLink).click();
    }

    public boolean isAtMcqCreatePage() {
        return WaitUtils.waitForUrlContains(driver, "/dashboard/mcq/create");
    }

    public boolean isAtMcqListPage() {
        return WaitUtils.waitForUrlContains(driver, "/dashboard/mcq");
    }
}
