package tech.innospace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tech.innospace.utils.WaitUtils;

import static tech.innospace.utils.Constants.BASE_URL;
import static tech.innospace.utils.Utils.clickDropdownAndSelectItem;

public class McqListPage {
    private final WebDriver driver;

    private final By classField = By.xpath("//span[@class='ant-select-selection-placeholder' and text()='Class']/preceding-sibling::*[1]/descendant::input");
    private final By courseField = By.xpath("//span[@class='ant-select-selection-placeholder' and text()='Course']/preceding-sibling::*[1]/descendant::input");
    private final By subjectField = By.xpath("//span[@class='ant-select-selection-placeholder' and text()='Subject']/preceding-sibling::*[1]/descendant::input");
    private final By chapterField = By.xpath("//span[@class='ant-select-selection-placeholder' and text()='Chapter']/preceding-sibling::*[1]/descendant::input");
    private final By topicField = By.xpath("//span[@class='ant-select-selection-placeholder' and text()='Topic']/preceding-sibling::*[1]/descendant::input");

    public McqListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(BASE_URL + "/dashboard/mcq");
    }

    public void selectClassItem(String itemLabel) {
        clickDropdownAndSelectItem(classField, itemLabel, driver);
    }

    public void selectCourseItem(String itemLabel) {
        clickDropdownAndSelectItem(courseField, itemLabel, driver);
    }

    public void selectSubjectItem(String itemLabel) {
        clickDropdownAndSelectItem(subjectField, itemLabel, driver);
    }

    public void selectChapterItem(String itemLabel) {
        clickDropdownAndSelectItem(chapterField, itemLabel, driver);
    }

    public void selectTopicItem(String itemLabel) {
        clickDropdownAndSelectItem(topicField, itemLabel, driver);
    }

    public WebElement getElementsByQuestionText(String questionText) {
        By questionLocator = By.xpath("//*[text()='" + questionText + "']");
        return driver.findElement(questionLocator);
    }

    public WebElement getDifficultyElement(String difficulty) {
        By difficultyLocator = By.xpath("//td[@class='ant-table-cell' and text()='" + difficulty + "']");
        return WaitUtils.waitForElementVisible(driver, difficultyLocator);
    }

    public String getRowId() {
        WebElement element = WaitUtils.waitForElementVisible(driver, By.xpath("//tr[@class='ant-table-row ant-table-row-level-0']"));
        return element.getAttribute("data-row-key");
    }

    public void clickEditButton(String rowId) {
        WebElement editElement = WaitUtils.waitForElementVisible(driver, By.xpath("//a[@href='/dashboard/mcq/edit/" + rowId + "']"));
        editElement.click();
    }

    public void clickDeleteButton(String rowId) {
        WebElement deleteElement = WaitUtils.waitForElementVisible(driver, By.xpath("//tr[@data-row-key='" + rowId + "']/td[last()]/div/div[last()]/button"));
        deleteElement.click();

        WebElement confirmDeleteButton = WaitUtils.waitForElementVisible(driver, By.xpath("//div[contains(@class, 'ant-modal-confirm-btns')]//button[span[text()='OK']]"));
        confirmDeleteButton.click();
    }
}
