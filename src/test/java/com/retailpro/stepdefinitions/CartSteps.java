package com.retailpro.stepdefinitions;

import com.retailpro.pages.CartPage;
import com.retailpro.pages.InventoryPage;
import com.retailpro.pages.LoginPage;
import com.retailpro.utils.ConfigReader;
import com.retailpro.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartSteps {
    private final InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
    private final CartPage cartPage = new CartPage(DriverFactory.getDriver());

    @Given("the user is logged in")
    public void userLoggedIn() {
        new LoginPage(DriverFactory.getDriver()).open()
                .login(ConfigReader.get("username"), ConfigReader.get("password"));
        Assert.assertTrue(inventoryPage.isLoaded());
    }

    @When("the user adds {int} products to the cart")
    public void addProducts(int count) {
        inventoryPage.addFirstProducts(count);
    }

    @Then("the cart badge shows {int}")
    public void cartBadgeShows(int expected) {
        Assert.assertEquals(inventoryPage.cartCount(), expected);
    }

    @When("the user opens the cart")
    public void openCart() {
        inventoryPage.openCart();
    }

    @Then("the cart contains {int} items")
    public void cartContains(int expected) {
        Assert.assertEquals(cartPage.itemCount(), expected);
    }

    @When("the user removes the first item from the cart")
    public void removeFirst() {
        cartPage.removeFirstItem();
    }

    @When("the user sorts products by {string}")
    public void sortBy(String option) {
        inventoryPage.sortBy(option);
    }

    @Then("the products are sorted by price ascending")
    public void sortedByPriceAsc() {
        List<Double> actual = inventoryPage.productPrices();
        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);
        Assert.assertEquals(actual, expected);
    }
}
