package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.WebDriverManager;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        WebDriverManager.getDriver().navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage();
    }

    @When("the user enters {string} into the username field")
    public void the_user_enters_into_the_username_field(String username) {
        loginPage.enterUsername(username);
    }

    @When("the user enters {string} into the password field")
    public void the_user_enters_into_the_password_field(String password) {
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("the user should be redirected to the home page")
    public void the_user_should_be_redirected_to_the_home_page() {
        assertTrue(loginPage.isDashboardVisible());
        WebDriverManager.quitDriver();
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        assertTrue(loginPage.isErrorMessageVisible());
        WebDriverManager.quitDriver();
    }
}
