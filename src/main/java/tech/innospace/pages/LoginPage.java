package tech.innospace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tech.innospace.utils.EnvLoader;
import tech.innospace.utils.WaitUtils;

import static tech.innospace.utils.Constants.BASE_URL;

public class LoginPage {

    private final WebDriver driver;

    private final By emailField = By.id("loginForm_email");
    private final By passwordField = By.id("loginForm_password");
    private final By loginButton = By.xpath("//button[@type='submit' and contains(@class, 'login-form-button') and span[text()='Log in']]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(BASE_URL + "/login");
    }

    public void enterEmail(String email) {
        WebElement emailInput = WaitUtils.waitForElementVisible(driver, emailField);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = WaitUtils.waitForElementVisible(driver, passwordField);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void submitLogin() {
        WaitUtils.waitForClickable(driver, loginButton);
        driver.findElement(loginButton).click();
    }

    public void loginWithValidCredentials() {
        open();
        enterEmail(EnvLoader.get("email"));
        enterPassword(EnvLoader.get("password"));
        submitLogin();
    }

    public boolean isAtDashboard() {
        return WaitUtils.waitForUrlContains(driver, "/dashboard");
    }
}
