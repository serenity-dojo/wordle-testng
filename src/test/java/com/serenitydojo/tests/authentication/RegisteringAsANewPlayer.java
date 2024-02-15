package com.serenitydojo.tests.authentication;

import com.serenitydojo.model.Player;
import com.serenitydojo.pageobjects.HowToPlayModal;
import com.serenitydojo.pageobjects.LoginPage;
import com.serenitydojo.pageobjects.SignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisteringAsANewPlayer {

    WebDriver driver;
    Player player;

    @BeforeMethod
    public void setupDriver() {
        driver = new ChromeDriver();
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

        LoginPage loginPage = new LoginPage(driver);
        loginPage.createAccount();

        // Register new user
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpAs(player);

        // Now try to log on
        loginPage.loginAs(player);

        // Check that we are on the game page
        HowToPlayModal howToPlayModal = new HowToPlayModal(driver);
        assertThat(howToPlayModal.getTitle()).isEqualTo("How to play");
    }
}
