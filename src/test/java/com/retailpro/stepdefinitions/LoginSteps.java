package com.retailpro.stepdefinitions;

import com.retailpro.pages.InventoryPage;
import com.retailpro.pages.LoginPage;
import com.retailpro.utils.ConfigReader;
import com.retailpro.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {
    private final LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private final InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());

    @Given("the user is on the login page")
    public void userOnLoginPage() {
        loginPage.open();
    }

    @When("the user logs in with valid credentials")
    public void loginWithValidCredentials() {
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
    }

    @When("the user logs in with username {string} and password {string}")
    public void loginWith(String user, String pass) {
        loginPage.login(user, pass);
    }

    @Then("the products page is displayed")
    public void productsPageDisplayed() {
        Assert.assertTrue(inventoryPage.isLoaded(), "Products page not displayed");
    }

    @Then("the error message contains {string}")
    public void errorContains(String expected) {
        Assert.assertTrue(loginPage.getErrorMessage().contains(expected),
                "Unexpected error: " + loginPage.getErrorMessage());
    }
}
