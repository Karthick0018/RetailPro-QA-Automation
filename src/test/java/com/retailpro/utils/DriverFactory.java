package com.retailpro.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {}

    public static void initDriver() {
        String browser = ConfigReader.get("browser").toLowerCase();
        boolean headless = ConfigReader.getBoolean("headless");
        WebDriver driver;
        switch (browser) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions o = new FirefoxOptions();
                if (headless) o.addArguments("-headless");
                driver = new FirefoxDriver(o);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions o = new EdgeOptions();
                if (headless) o.addArguments("--headless=new");
                driver = new EdgeDriver(o);
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions o = new ChromeOptions();
                if (headless) o.addArguments("--headless=new");
                o.addArguments("--start-maximized","--remote-allow-origins=*", "--disable-notifications",
                        "--disable-save-password-bubble","--disable-gpu","--no-sandbox");
                o.setExperimentalOption("prefs", java.util.Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false));
                driver = new ChromeDriver(o);
            }
        }
        //driver.manage().window().maximize();
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
