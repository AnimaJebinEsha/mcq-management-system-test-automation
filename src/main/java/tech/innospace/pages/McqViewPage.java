package tech.innospace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tech.innospace.utils.WaitUtils;

import static tech.innospace.utils.Constants.BASE_URL;

public class McqViewPage {
    private final WebDriver driver;

    public McqViewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String id) {
        driver.get(BASE_URL + "/dashboard/mcq/view/" + id);
        WaitUtils.waitForSeconds(5);
    }

    public String getQuestionValue() {
        WebElement questionElement = WaitUtils.waitForElementVisible(driver, By.xpath("//h5[text()='Question']/following-sibling::div/p[1]"));

        return questionElement.getText();
    }

    public String getDifficultyValue() {
        WebElement difficultyElement = WaitUtils.waitForElementVisible(driver, By.xpath("//h5[text()='Difficulty']/following-sibling::p"));

        return difficultyElement.getText();
    }

    public boolean isCorrectOptionSelected(String optionNo) {
        try {
            WebElement checkbox = WaitUtils.waitForElementVisible(driver, By.xpath("//span[@class='pr-2' and contains(text(), '" + optionNo + "')]/preceding-sibling::span[@class='pr-1']"));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
