package com.serenitydojo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ToastorPopup extends WordlePageComponent{

    private static final By TOASTER_MESSAGE = By.cssSelector(".Toastify [role=alert]");

    public ToastorPopup(WebDriver driver) {
        super(driver);
    }

    public String getMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TOASTER_MESSAGE));
        return driver.findElement(TOASTER_MESSAGE).getText();
    }
}
