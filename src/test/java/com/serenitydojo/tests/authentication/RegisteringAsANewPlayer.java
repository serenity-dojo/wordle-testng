package com.serenitydojo.tests.authentication;

import com.serenitydojo.model.Player;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisteringAsANewPlayer {

    WebDriver driver;
    Player player;
    WebDriverWait wait;

    @BeforeMethod
    public void setupDriver() {
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.of(3,ChronoUnit.SECONDS));
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @BeforeMethod
    public void setupPlayer() {
        player = Player.somePlayer();
    }

    @Test(description = "A new player should be able to sign up by providing a name, a password and an email")
    public void aNewPlayerSignsUp() {
        driver.get("http://localhost:5173/");
        driver.findElement(By.linkText("Create Account")).click();

        // Register new user
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-account")));
        driver.findElement(By.id("name")).sendKeys(player.name());
        driver.findElement(By.id("email")).sendKeys(player.email());
        driver.findElement(By.id("password")).sendKeys(player.password());
        driver.findElement(By.id("create-account")).click();

        // Now try to login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        driver.findElement(By.id("name")).sendKeys(player.name());
        driver.findElement(By.id("password")).sendKeys(player.password());
        driver.findElement(By.id("login")).click();

        // Check that we are on the game page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-title")));
        wait.until(ExpectedConditions.urlContains("/game"));
        assertThat(driver.findElement(By.cssSelector(".modal-title")).getText()).isEqualTo("How to play");
    }
}
