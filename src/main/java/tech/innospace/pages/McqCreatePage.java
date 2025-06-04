package tech.innospace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tech.innospace.utils.WaitUtils;

public class McqCreatePage {
    private final WebDriver driver;

    private final By classField = By.id("mcqForm_classes");
    private final By courseField = By.id("mcqForm_course");
    private final By subjectField = By.id("mcqForm_subject");
    private final By chapterField = By.id("mcqForm_chapter");
    private final By topicField = By.id("mcqForm_topic");

    public McqCreatePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectClassItem(String itemLabel) {
        clickDropdownAndSelectItem(classField, itemLabel);
    }

    public void selectCourseItem(String itemLabel) {
        clickDropdownAndSelectItem(courseField, itemLabel);
    }

    public void selectSubjectItem(String itemLabel) {
        clickDropdownAndSelectItem(subjectField, itemLabel);
    }

    public void selectChapterItem(String itemLabel) {
        clickDropdownAndSelectItem(chapterField, itemLabel);
    }

    public void selectTopicItem(String itemLabel) {
        clickDropdownAndSelectItem(topicField, itemLabel);
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
        WebElement checkboxInput = WaitUtils.waitForElementVisible(driver, By.xpath("//input[@id='mcqForm_options_" + 3 + "_isCorrect']"));
        WebElement checkbox = checkboxInput.findElement(By.xpath(".."));

        System.out.println(checkbox.isSelected());

        checkbox.click();
    }

    public void clickSubmitButton() {
        WebElement submitButton = WaitUtils.waitForElementVisible(driver,
                By.xpath("//button[span[normalize-space(text())='Submit']]")
        );
        submitButton.click();
    }


    private void clickDropdownAndSelectItem(By dropdownWrapper, String itemLabel) {
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
