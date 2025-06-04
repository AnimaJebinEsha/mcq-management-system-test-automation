package tech.innospace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tech.innospace.utils.WaitUtils;

public class McqUpdatePage {
    private final WebDriver driver;

    public McqUpdatePage(WebDriver driver) {
        this.driver = driver;
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
        paragraph.clear();
        paragraph.sendKeys(question);

        driver.switchTo().defaultContent();
    }

    public void setOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            driver.switchTo().frame("tinymceEditor_options_" + i + "_text_ifr");

            WebElement paragraph = WaitUtils.waitForElementVisible(driver, By.tagName("p"));
            paragraph.clear();
            paragraph.sendKeys(options[i]);

            driver.switchTo().defaultContent();
        }
    }

    public void selectCorrectOption(int option_no) {
        WebElement checkbox = WaitUtils.waitForElementVisible(driver, By.xpath("//input[@id='mcqForm_options_"+option_no+"_isCorrect']/parent::span/parent::label"));
        checkbox.click();
    }

    public void unSelectCorrectOption(int option_no) {
        WebElement checkbox = WaitUtils.waitForElementVisible(driver, By.xpath("//input[@id='mcqForm_options_"+option_no+"_isCorrect']/parent::span/parent::label"));
        checkbox.click();
    }

    public void clickUpdateButton() {
        WebElement submitButton = WaitUtils.waitForElementVisible(driver,
                By.xpath("//button[span[normalize-space(text())='Update']]")
        );
        submitButton.click();
    }
}
