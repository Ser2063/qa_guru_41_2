import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestBoxTests {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @Test
    void fillFormTest() {
        open("/text-box");
        $("#userName").setValue("Sergey");
        $("[id=userEmail]").setValue("Sergey@sergey.ru");
        $("[id=currentAddress]").setValue("Adress 1");
        $("[id=permanentAddress]").setValue("Adress 2");
        $("[id=submit]").click();
        $("#output #name").shouldHave(text("Sergey"));
        $("[id=output]").$("[id=email]").shouldHave(text("Sergey@sergey.ru"));
        $("[id=output]").$("[id=currentAddress]").shouldHave(text("Adress 1"));
        $("[id=output]").$("[id=permanentAddress]").shouldHave(text("Adress 2"));

    }

    @Test
    void invalidEmailTest() {
        open("/text-box");
        $("#userEmail").setValue("not-an-email");
        $("#submit").click();

        // Проверяем, что поле email подсветилось красным (появился CSS-класс ошибки)
        $("#userEmail").shouldHave(cssClass("field-error"));
        // Проверяем, что блок с результатом не отобразился
        $("#output").shouldNot(be(visible));


    }

    @Test
    void emptyFormSubmitTest() {
        open("/text-box");
        $("#submit").click();

        // Проверяем, что блок с выводом данных отсутствует в DOM или не виден
        $("#output").shouldNot(be(visible));
        // Дополнительно: проверяем, что значения не заполнились (если id=name все же есть в DOM)
        $("#output #name").shouldNot(exist);

    }

    @Test
    void incompleteEmailTest() {
        open("/text-box");
        $("#userName").setValue("Sergey");
        $("#userEmail").setValue("sergey@missingdomain"); // Без .ru/.com и т.д.
        $("#submit").click();

        // Проверяем наличие класса ошибки у поля
        $("#userEmail").shouldHave(cssClass("field-error"));
        // Проверяем, что имя "Sergey" не появилось в итоговом блоке, так как форма не отправилась
        $("#output").shouldNot(be(visible));

    }

}
