import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemocaUIBasicTests {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000;
    }

    @Test
    void fillingFormFieldsCSS() {
        open("/automation-practice-form");
        $("#firstName").setValue("Serg");
        $("#lastName").setValue("Rzh");
        //$("#userEmail").setValue("jc@mail.ru");
        $$("#genterWrapper label").filterBy(text("Male")).first().click();
        $("#userNumber").setValue("12345678901");

        // Дата рождения
        $("#dateOfBirthInput").click();
        $("select.react-datepicker__month-select").selectOption("January");
        $("select.react-datepicker__year-select").selectOption("1984");
        //$("div.react-datepicker__day:not(.react-datepicker__day--outside-month)").click();
        $("div.react-datepicker__day--012").click();


        //нажимаем кнопку
        $("button#submit").click();

        //реконсиляция

        $(".modal-content").shouldBe(visible);

        //  Проверка по таблице
        $(".table-responsive")
                .$(byText("Student Name")).parent().shouldHave(text("Serg Rzh"));
        $(".table-responsive")
                .$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive")
                .$(byText("Mobile")).parent().shouldHave(text("1234567890"));
        $(".table-responsive")
                .$(byText("Date of Birth")).parent().shouldHave(text("12 January,1984"));

        // Закрытие модального окна
        $("#closeLargeModal").click();

        sleep(10000);
    }
}
