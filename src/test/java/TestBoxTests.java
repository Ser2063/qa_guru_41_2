import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestBoxTests {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
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

        sleep(10000);
    }
}
