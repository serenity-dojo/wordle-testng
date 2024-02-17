package com.serenitydojo.pageobjects;

import com.serenitydojo.model.Player;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage extends WordlePageComponent {
    private static final By NAME_FIELD = By.id("name");
    private static final By EMAIL_FIELD = By.id("email");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By COUNTRY_DROPDOWN = By.id("country");
    private static final By CREATE_ACCOUNT_BUTTON = By.id("create-account");
    private static final By ERROR_MESSAGES = By.cssSelector("[class*=text-red]");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void signUpAs(Player player) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_ACCOUNT_BUTTON));
        driver.findElement(NAME_FIELD).sendKeys(player.name());
        driver.findElement(EMAIL_FIELD).sendKeys(player.email());
        driver.findElement(PASSWORD_FIELD).sendKeys(player.password());
        new Select(driver.findElement(COUNTRY_DROPDOWN)).selectByVisibleText("United Kingdom");
        driver.findElement(CREATE_ACCOUNT_BUTTON).click();
    }

    public String toasterMessage() {
        return new ToastorPopup(driver).getMessage();
    }

    public List<String> errorMessages() {
        return driver.findElements(ERROR_MESSAGES)
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
