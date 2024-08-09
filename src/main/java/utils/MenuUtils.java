package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuUtils {

    public static void clickMenuItem(WebDriver driver, WebDriverWait wait, String menuItemText) {
        WebElement menuItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + menuItemText + "']")));
        menuItem.click();
    }
}
