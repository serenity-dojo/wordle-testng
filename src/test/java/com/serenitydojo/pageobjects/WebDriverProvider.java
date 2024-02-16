package com.serenitydojo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverProvider {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (webDriver.get() == null) {
            ChromeOptions options = new ChromeOptions();//.addArguments("headless");
            WebDriver driver = new ChromeDriver(options);
            webDriver.set(driver);
        }
        return webDriver.get();
    }

    public static void quitDriver() {
        webDriver.get().quit();
        webDriver.remove();
    }
}
