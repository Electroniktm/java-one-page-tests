import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginTests {

    @BeforeEach
    void setup() {

    closeWebDriver();
}

    @Test
    void successfulAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
    //$("[id=login-input]").setValue("user1");
    //$("#login-input").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void successfulAuthorizationByEnterTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1").pressEnter();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
        //closeWebDriver();
    }

    @Test
    void wrongPasswordAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("WRONG PASSWORD");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
        //closeWebDriver();
    }

    @Test
    void wrongLoginUpperCaseAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("USER1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
        //closeWebDriver();
    }

    @Test
    void emptyLoginAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Login is required (minimum 3 characters)"));
        //closeWebDriver();
    }

    @Test
    void emptyPasswordAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("USER1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Password is required (minimum 6 characters)"));
        //closeWebDriver();
    }

    @Test
    void emptyLoginPasswordAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text(
                "Login and password are required (minimum 3 and 6 characters)"));
    }

    @Test
    void shortLoginAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("us");
        $("[data-testid=password-input]").setValue("WRONG PASSWORD");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Login must be at least 3 characters"));
    }

    @Test
    void shortPasswordAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("pass");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Password must be at least 6 characters"));
    }

    @Test
    void shortPasswordAndLoginAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("us");
        $("[data-testid=password-input]").setValue("pass");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Login must be at least 3 characters"));
    }

    @Test
    void specialCharactersInLoginTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user@#$%");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void specialCharactersInPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("pass@#$word!");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void whitespaceAtStartOfLoginTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue(" user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void whitespaceAtEndOfLoginTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1 ");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void whitespaceAtBothSidesOfPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue(" password1 ");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void longLoginTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        String longLogin = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        $("[data-testid=login-input]").setValue(longLogin);
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void longPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        String longPassword = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue(longPassword);
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void loginWithNumbersTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user123");
        $("[data-testid=password-input]").setValue("pass1234");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void loginWithSpecialSymbolsTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user_123!");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void unicodeCharactersInPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("пароль123");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void emptyFieldsWithEnterKeyTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("Login and password are required"));
    }

    @Test
    void onlyLoginFilledWithEnterTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("Password is required"));
    }

    @Test
    void onlyPasswordFilledWithEnterTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=password-input]").setValue("password1").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("Login is required"));
    }

    @Test
    void logoutAfterSuccessfulAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));

        $("[data-testid=logout-button]").click();

        $("[data-testid=login-input]").should(visible);
    }
}
