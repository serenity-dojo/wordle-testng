package com.serenitydojo.tests.authentication;

import com.serenitydojo.model.Player;
import com.serenitydojo.pageobjects.HowToPlayModal;
import com.serenitydojo.pageobjects.LoginPage;
import com.serenitydojo.pageobjects.SignUpPage;
import com.serenitydojo.pageobjects.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UsernameAndPasswordRules {

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

    @Test(description = "A new player should provide a well-formed email",
          dataProvider = "validAndInvalidEmails")
    public void shouldProvideAValidEmail(String email, String expectedErrorMessage) {
        driver.get("http://localhost:5173/signup");

        // Register new user
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpAs(player.withEmail(email));
        String displayedErrorMessage = signUpPage.toasterMessage();
        // And check for the error message in the toaster dialog
        assertThat(displayedErrorMessage).contains(expectedErrorMessage);
    }

    @DataProvider(name = "validAndInvalidEmails")
    public static Object[][] validAndInvalidEmails() {
        return new Object[][] {
                {"not-an-email","Please enter a valid email!"},
                {"12345","Please enter a valid email!"},
                {"not@an-email","Please enter a valid email!"},
                {"not-an-email.com","Please enter a valid email!"}
        };
    }
}
