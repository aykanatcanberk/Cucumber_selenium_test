// src/main/java/utils/BrowserUtils.java
package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BrowserUtils {

    public static void clickButtonByXPath(WebDriver driver, WebDriverWait wait, String xPath) {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        button.click();
    }

    public static void clickElementByCssSelector(WebDriver driver, WebDriverWait wait, String cssSelector) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
        element.click();
    }

    public static void clickElementByXPath(WebDriver driver, WebDriverWait wait, String xPath) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        element.click();
    }

    public static void enterTextInField(WebDriver driver, WebDriverWait wait, By locator, String text) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
        field.sendKeys(text);
    }

    public static void uploadFile(WebDriver driver, WebDriverWait wait, By locator, String filePath) {
        WebElement uploadElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        uploadElement.sendKeys(filePath);
    }

    public static void verifyUrl(WebDriver driver, WebDriverWait wait, String expectedUrl) {
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals(expectedUrl)) {
            throw new AssertionError("Expected URL: " + expectedUrl + " but found: " + currentUrl);
        }
    }

    public static void verifySearchResults(WebDriver driver, WebDriverWait wait, String expectedText) {
        try {
            List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(), '" + expectedText + "')]")));
            if (results.isEmpty()) {
                throw new AssertionError("Expected text: " + expectedText + " not found in search results.");
            }
        } catch (Exception e) {
            throw new AssertionError("An error occurred while verifying search results: " + e.getMessage());
        }
    }
}
