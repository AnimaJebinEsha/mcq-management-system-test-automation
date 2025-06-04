package tech.innospace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tech.innospace.utils.WaitUtils;

import static tech.innospace.utils.Utils.clickDropdownAndSelectItem;

public class McqCreatePage {
    private final WebDriver driver;

    private final By classField = By.id("mcqForm_classes");
    private final By courseField = By.id("mcqForm_course");
    private final By subjectField = By.id("mcqForm_subject");
    private final By chapterField = By.id("mcqForm_chapter");
    private final By topicField = By.id("mcqForm_topic");
    private final By successNotification = By.xpath("//div[@class='ant-notification-notice-message' and text()='Successfully created.']");

    public McqCreatePage(WebDriver driver) {
        this.driver = driver;
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

    public void selectDifficulty(String label) {
        WebElement option = WaitUtils.waitForElementVisible(driver, By.xpath(
                "//div[@id='mcqForm_difficulty']//label[.//span[contains(@class,'ant-radio-label') and normalize-space(text())='" + label + "']]"
        ));
        option.click();
    }

    public void writeQuestion(String question) {
        driver.switchTo().frame("tinymceEditor_questionText_ifr");

        WebElement paragraph = WaitUtils.waitForElementVisible(driver, By.tagName("p"));
        paragraph.sendKeys(question);

        driver.switchTo().defaultContent();
    }

    public void setOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            driver.switchTo().frame("tinymceEditor_options_" + i + "_text_ifr");

            WebElement paragraph = WaitUtils.waitForElementVisible(driver, By.tagName("p"));
            paragraph.sendKeys(options[i]);

            driver.switchTo().defaultContent();
        }
    }

    public void selectCorrectOption(int option_no) {
        WebElement checkbox = WaitUtils.waitForElementVisible(driver, By.xpath("//input[@id='mcqForm_options_"+option_no+"_isCorrect']/parent::span/parent::label"));
        checkbox.click();
    }

    public void clickSubmitButton() {
        WebElement submitButton = WaitUtils.waitForElementVisible(driver,
                By.xpath("//button[span[normalize-space(text())='Submit']]")
        );
        submitButton.click();
    }

    public boolean isSuccessNotificationVisible() {
        return WaitUtils.waitForElementVisible(driver, successNotification) != null;
    }
}
