package com.retailpro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {
    private final By title = By.cssSelector(".title");
    private final By items = By.cssSelector(".inventory_item");
    private final By itemNames = By.cssSelector(".inventory_item_name");
    private final By itemPrices = By.cssSelector(".inventory_item_price");
    private final By addButtons = By.cssSelector("button[data-test^='add-to-cart']");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By sortDropdown = By.cssSelector("[data-test='product-sort-container']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return text(title).equalsIgnoreCase("Products");
    }

    public int productCount() {
        return all(items).size();
    }

    public void addFirstProducts(int count) {
        List<WebElement> buttons = all(addButtons);
        for (int i = 0; i < count && i < buttons.size(); i++) {
            buttons.get(i).click();
        }
    }

    public int cartCount() {
        return isPresent(cartBadge) ? Integer.parseInt(text(cartBadge)) : 0;
    }

    public void openCart() {
        click(cartLink);
    }

    public void sortBy(String visibleText) {
        new Select(visible(sortDropdown)).selectByVisibleText(visibleText);
    }

    public List<String> productNames() {
        return all(itemNames).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<Double> productPrices() {
        return all(itemPrices).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }
}
