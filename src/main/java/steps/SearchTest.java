package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtils;
import utils.FormUtils;
import utils.MenuUtils;
import utils.SearchLogin;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchTest {
    WebDriver driver;
    WebDriverWait wait;
    private SearchLogin searchLogin;
    private String initialUrl;
    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com";

    @Given("the user has logged in to the application")
    public void theUserHasLoggedInToTheApplication() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        searchLogin = new SearchLogin(driver);
        searchLogin.login("Admin", "admin123");
    }

    @When("the user selects the Buzz menu item")
    public void theUserSelectsTheBuzzMenuItem() {
        MenuUtils.clickMenuItem(driver, wait, "Buzz");
    }

    @Then("the user should be directed to the Buzz page")
    public void theUserShouldBeDirectedToTheBuzzPage() {
        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/web/index.php/buzz/viewBuzz"));
        assertEquals(BASE_URL + "/web/index.php/buzz/viewBuzz", driver.getCurrentUrl());
    }

    @And("the user posts something {string}")
    public void theUserPostsAMessageSaying(String message) {
        WebElement postTextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-buzz-post-input")));
        postTextArea.clear(); // Clear the text area before entering a new message
        postTextArea.sendKeys(message);

        WebElement postButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".oxd-button--main")));
        postButton.click();

        // Wait for the page to refresh and new posts to be visible
        driver.navigate().refresh();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Reinitialize WebDriverWait if necessary

        // Ensure that posts are visible before proceeding
        List<WebElement> posts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".orangehrm-buzz-post-body")));
        boolean postFound = posts.stream()
                .anyMatch(post -> post.findElement(By.cssSelector(".orangehrm-buzz-post-body-text")).getText().contains(message));

        Assert.assertTrue("The post with the message was not found.", postFound);
    }


    @When("the user enters {string} in the search bar")
    public void theUserEntersInTheSearchBar(String searchTerm) {
        WebElement searchBar = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        searchBar.clear();
        searchBar.sendKeys(searchTerm);
    }

    @And("the user performs the search")
    public void theUserPerformsTheSearch() {
        WebElement searchButton = driver
                .findElement(By.xpath("//button[@type='button' and @role='none']"));
        searchButton.click();
    }

    @Then("the search results should contain {string}")
    public void theSearchResultsShouldContain(String expectedText) {
        WebElement resultsElement = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//*[@id='app']/div[1]/div[1]/aside/nav/div[2]/ul/li/a")));
        List<WebElement> resultItems = driver
                .findElements(By.xpath("//*[@id='app']/div[1]/div[1]/aside/nav/div[2]/ul/li/a/span[text()='" + expectedText + "']"));
        boolean found = !resultItems.isEmpty();

        Assert.assertTrue("The search results did not contain the expected text.", found);
    }

    @When("the user clicks on the PIM menu item")
    public void theUserClicksOnThePIMMenuItem() {
        MenuUtils.clickMenuItem(driver, wait, "PIM");
    }

    @And("the user clicks on the Add button")
    public void theUserClicksOnTheAddButton() {
        BrowserUtils.clickButtonByXPath(driver, wait, "//button[text()=' Add ']");
    }

    @And("the user fills in the First Name and Last Name fields and clicks Save")
    public void theUserFillsInTheFirstNameAndLastNameFieldsAndClicksSave() {
        initialUrl = driver.getCurrentUrl(); // Capture the initial URL
        FormUtils.fillAndSaveName(driver, wait,"John", "Doe");
    }

    @Then("the user should be redirected to another page")
    public void theUserShouldBeRedirectedToAnotherPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Wait for the URL to change after the save button is clicked
            wait.until(driver -> !driver.getCurrentUrl().equals(initialUrl));

            // Verify that the URL has changed
            String currentUrl = driver.getCurrentUrl();
            Assert.assertNotEquals("URL should have changed after save operation", initialUrl, currentUrl);
        } catch (Exception e) {
            Assert.fail("Redirection failed or an error occurred: " + e.getMessage());
        }
    }

    @When("The user clicks on the profile picture")
    public void theUserClicksOnTheProfilePicture() {
        BrowserUtils.clickElementByCssSelector(driver, wait, ".oxd-userdropdown-img");
    }

    @And("The user selects Logout from the dropdown menu")
    public void theUserSelectsLogoutFromTheDropdownMenu() {
        BrowserUtils.clickButtonByXPath(driver, wait, "//ul[@class='oxd-dropdown-menu']//a[text()='Logout']");
    }

    @Then("The user should be redirected to the login page")
    public void theUserShouldBeRedirectedToTheLoginPage() {
        BrowserUtils.verifyUrl(driver, wait, BASE_URL + "/web/index.php/auth/login");
    }

    @When("the user clicks on the Admin menu item")
    public void theUserIsOnTheAdminPage() {
        MenuUtils.clickMenuItem(driver, wait, "Admin");
    }

    @Then("Click Dropdown Arrow")
    public void clickOnDropdownArrow() {
        BrowserUtils.clickElementByXPath(driver, wait, "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i");
    }

    @Then("Chose dropdown option {string}")
    public void selectDropdownOption(String optionText) {
        BrowserUtils.clickElementByXPath(driver, wait, "//div[@role='option' and .//span[text()='" + optionText + "']]");
    }

    @And("The user clicks Search button")
    public void clickSearchButton() throws InterruptedException {
        BrowserUtils.clickButtonByXPath(driver, wait, "//button[@data-v-10d463b7='' and @type='submit']");
        Thread.sleep(1000); // Ensure any asynchronous actions are completed
    }

    @When("Click on the PIM menu item")
    public void ClicksOnThePIMMenuItem() {
        MenuUtils.clickMenuItem(driver, wait, "PIM");
    }

    @Then("Click on the checkbox item to choose all of records")
    public void selectAllRecords() {
        BrowserUtils.clickElementByXPath(driver, wait, "(//span[contains(@class, 'oxd-checkbox-input')])[1]");
    }

    @Then("Click on Delete Selected button")
    public void clickDeleteSelectedButton() {
        BrowserUtils.clickButtonByXPath(driver, wait, "//button[contains(., 'Delete Selected')]");
    }

    @Then("Confirm to deletion of all records")
    public void confirmDeletion() {
        BrowserUtils.clickButtonByXPath(driver, wait, "//button[contains(., 'Yes, Delete')]");
    }

    @When("User clicks on the My Info menu")
    public void userClicksOnTheMyInfoMenu() {
        MenuUtils.clickMenuItem(driver, wait, "My Info");
    }

    @And("User navigates to the profile picture page")
    public void userNavigatesToTheProfilePicturePage() {
        WebElement profilePicture = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='employee-image']")));
        profilePicture.click();
    }

    @Then("Upload the profile picture and save it")
    public void uploadTheProfilePictureAndSaveIt() {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "/src/main/resources/Image/purple.jpg";

        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        fileInput.sendKeys(filePath);

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'oxd-button--secondary')]")));
        saveButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@type='submit' and contains(@class, 'oxd-button--secondary')]")));
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        if (driver != null) {
            driver.quit();
        }
    }
}
