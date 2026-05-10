import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class DemocaUiTests {

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
        $("#userEmail").setValue("jc@mail.ru");
        $$("#genterWrapper label").filterBy(text("Male")).first().click();
        $("#userNumber").setValue("12345678901");

        // Дата рождения
        $("#dateOfBirthInput").click();
        $("select.react-datepicker__month-select").selectOption("January");
        $("select.react-datepicker__year-select").selectOption("1984");
        //$("div.react-datepicker__day:not(.react-datepicker__day--outside-month)").click();
        $("div.react-datepicker__day--012").click();

        // Предметы
        $("#subjectsInput").setValue("Bio").pressEnter();

        //Хобби
        $("label[for='hobbies-checkbox-2']").click();
        $$("#hobbiesWrapper label").filterBy(text("Sports")).first().click();

        //Адрес
        $("textarea#currentAddress").setValue("Адрес");

        //Картинка
        $("#uploadPicture").uploadFromClasspath("img.png"); //добавлена папка test/resources вложен файл img.png
        //$("input#uploadPicture").sendKeys("C:\\Users\\mesoi\\IdeaProjects\\qa_guru_41_2\\img.png");

        //штат и город
        $("#state").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text("NCR")).click();
        $("#city").scrollTo().shouldBe(interactable).click();
        $$("div[class*='-option']").findBy(text("Delhi")).click();

        //нажимаем кнопку
        $("button#submit").click();

        //реконсиляция

        $(".modal-content").shouldBe(visible);

        //  Проверка по таблице
        $(".table-responsive")
                .$(byText("Student Name")).parent().shouldHave(text("Serg Rzh"));
        $(".table-responsive")
                .$(byText("Student Email")).parent().shouldHave(text("jc@mail.ru"));
        $(".table-responsive")
                .$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive")
                .$(byText("Mobile")).parent().shouldHave(text("1234567890"));
        $(".table-responsive")
                .$(byText("Date of Birth")).parent().shouldHave(text("12 January,1984"));
        $(".table-responsive")
                .$(byText("Subjects")).parent().shouldHave(text("Biology"));
        $(".table-responsive")
                .$(byText("Hobbies")).parent().shouldHave(text("Reading, Sports"));
        $(".table-responsive")
                .$(byText("Picture")).parent().shouldHave(text("img.png"));
        $(".table-responsive")
                .$(byText("Address")).parent().shouldHave(text("Адрес"));
        $(".table-responsive")
                .$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));

        // Закрытие модального окна
        $("#closeLargeModal").click();

        sleep(10000);
    }
}
