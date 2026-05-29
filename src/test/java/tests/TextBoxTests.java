package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends TestBase {

    @Test
    void successfulFillFormTest() {
        open("/text-box");
        $("[id=userName]").setValue("Alex Black");
        $("[id=userEmail]").setValue("alex@black.com");
        $("[id=currentAddress]").setValue("first address 1");
        $("[id=permanentAddress]").setValue("second address 2");
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("Alex Black"));
        $("[id=output] [id=email]").shouldHave(text("alex@black.com"));
        $("[id=output] [id=currentAddress]").shouldHave(text("first address 1"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("second address 2"));
    }

    // ==================== TEXT BOX FORM TESTS ====================

    @Test
    void textBoxMinimalFieldsTest() {
        open("/text-box");

        // Fill only Full Name (required field)
        $("#userName").setValue("John Doe");

        // Submit
        $("#submit").click();

        // Verify success - check the output section
        $("#output").shouldBe(visible);
        $("[id=output] [id=name]").shouldHave(text("John Doe"));
    }

    @Test
    void textBoxEmptyFieldsTest() {
        open("/text-box");

        // Don't fill anything, just click Submit
        $("#submit").click();

        // Verify output shows empty values
        $("#output").shouldBe(hidden);
    }

    @Test
    void textBoxTooLongNameTest() {
        open("https://demoqa.com/text-box");

        // Fill name with more than 25 characters (exceeding limit)
        $("#userName").setValue("JohnJohnJohnJohnJohnJohnJohnJohnJohnJohn");

        // Submit
        $("#submit").click();

        // Verify output shows the long name (no validation in this form)
        $("#output").shouldBe(visible);
        $("[id=output] [id=name]").shouldHave(text("JohnJohnJohnJohnJohnJohnJohnJohnJohnJohn"));
    }

    @Test
    void failEmailFillFormTest() {
        open("/text-box");
        $("[id=userName]").setValue("Alex Black");
        $("[id=userEmail]").setValue("alex@black");
        $("[id=currentAddress]").setValue("first address 1");
        $("[id=permanentAddress]").setValue("second address 2");
        $("[id=submit]").click();

        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(255, 0, 0)"));
    }
}