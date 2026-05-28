package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

   int month;
   int year;
   int day = LocalDate.now().getDayOfMonth();

    {
        month = LocalDate.now().getMonthValue();
        year =  LocalDate.now().getYear();
    }


    @BeforeAll
    static void beforeALL() {
        Configuration.browserSize = "1920x1280";
        Configuration.browser = "chrome";
//        Configuration.browserVersion = "144.0";
        Configuration.baseUrl = "https://demoqa.com";
//      Configuration.pageLoadStrategy = "eager";
//        Configuration.timeout = 10000; // default 4000
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    public class DatePickerHelper {
        public static void selectDateInReactDatePicker(LocalDate date) {
            int year = date.getYear();
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();

            $(".react-datepicker__month-select")
                    .shouldBe(visible)
                    .selectOption(String.valueOf(month - 1));

            $(".react-datepicker__year-select")
                    .shouldBe(visible)
                    .selectOption(String.valueOf(year));

            String monthNameEng = date.getMonth().getDisplayName(
                    java.time.format.TextStyle.FULL,
                    java.util.Locale.ENGLISH
            );
            String ariaLabel = String.format("Choose %s %d, %d", monthNameEng, day, year);

            $(By.xpath("//div[@role='gridcell' and contains(@aria-label, '" + ariaLabel + "')]"))
                    .shouldBe(visible)
                    .click();
        }
    }
}

