package com.retailpro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By cartItems = By.cssSelector(".cart_item");
    private final By removeButtons = By.cssSelector("button[data-test^='remove']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int itemCount() {
        return all(cartItems).size();
    }

    public void removeFirstItem() {
        click(removeButtons);
    }
}
