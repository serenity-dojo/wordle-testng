package com.serenitydojo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HowToPlayModal extends WordlePageComponent{

    private final By MODAL_TITLE = By.cssSelector(".modal-title");

    public HowToPlayModal(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_TITLE));
        return driver.findElement(MODAL_TITLE).getText();
    }
}
