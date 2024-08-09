package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.cssSelector("input[name='username']");
    private By passwordField = By.cssSelector("input[name='password']");
    private By loginButton = By.cssSelector(".oxd-button");
    private By dashboardElement = By.xpath("//h6[text()='Dashboard']");
    private By errorMessage = By.cssSelector(".oxd-alert-content");

    public LoginPage() {
        this.driver = WebDriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUsername(String username) {
        WebElement usernameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameElem.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElem.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    public boolean isDashboardVisible() {
        WebElement dashboardElem = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardElement));
        return dashboardElem.isDisplayed();
    }

    public boolean isErrorMessageVisible() {
        WebElement errorElem = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return errorElem.isDisplayed();
    }
}
