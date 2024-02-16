package com.serenitydojo.tests.authentication;

import com.serenitydojo.model.Player;
import com.serenitydojo.pageobjects.SignUpPage;
import com.serenitydojo.pageobjects.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidPasswordRules {

    WebDriver driver;
    Player player;

    @BeforeClass
    public void setupDriver() {
        driver = WebDriverProvider.getDriver();
    }

    @AfterClass
    public void closeDriver() {
        WebDriverProvider.quitDriver();
    }

    @BeforeMethod
    public void setupPlayer() {
        player = Player.somePlayer();
    }

    @Test(description = "A new player should provide a strong password",
          dataProvider = "invalidPasswords")
    public void shouldProvideAValidEmail(String password, String expectedErrorMessage) {
        driver.get("http://localhost:5173/signup");

        // Register new user
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpAs(player.withPassword(password));
        // Check the displayed error messages
        assertThat(signUpPage.errorMessages()).contains(expectedErrorMessage);
    }

    @DataProvider(name = "invalidPasswords")
    public static Object[][] invalidPasswords() {
        return new Object[][] {
                {"123","At least 8 characters required"},
                {"secretword","Password should contain with one uppercase"},
                {"SECRETWORD","Password should contain with one lowercase"},
                {"SecretWord","Password should contain with one special character"},
                {"SecretWord!","Password should contain with one number"},
        };
    }
}
