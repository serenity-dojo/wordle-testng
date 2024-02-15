package com.serenitydojo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class WordlePageComponent {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public WordlePageComponent(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.of(3, ChronoUnit.SECONDS));
    }
}
