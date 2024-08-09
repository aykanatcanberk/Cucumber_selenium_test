package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FormUtils {

    public static void fillAndSaveName(WebDriver driver, WebDriverWait wait, String firstName, String lastName) {

        BrowserUtils.enterTextInField(driver, wait, By.name("firstName"), firstName);
        BrowserUtils.enterTextInField(driver, wait, By.name("lastName"), lastName);
        BrowserUtils.clickButtonByXPath(driver, wait, "//button[text()=' Save ']");
    }
}
