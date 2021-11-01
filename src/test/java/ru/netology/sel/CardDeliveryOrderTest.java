package ru.netology.sel;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

class CardDeliveryOrderTest {
    //git add -f artifacts/app-card-delivery.jar
    //java -jar ./artifacts/app-card-delivery.jar ./gradlew test --info -Dselenide.headless=true

    String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    String addDate(String format) {
 //       "dd.MM.yyyy"
        LocalDate date = LocalDate.now().plusDays(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }


    @Test
    void shouldDirectInputOfValues() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Нижний Новгород");
        $("[placeholder='Дата встречи']").setValue(deleteString);
        $("[placeholder='Дата встречи']").setValue(addDate("dd.MM.yyyy"));
        $("[name='name']").setValue("Петров Петр");
        $("[name='phone']").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, ofSeconds(15));
//        $(byText("Личный кабинет")).shouldBe(visible, Duration.ofMillis(5000));
    }



    @Test
    void shouldNegativeDateInputOfValues() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Нижний Новгород");
        $("[placeholder='Дата встречи']").setValue(deleteString);
        $("[placeholder='Дата встречи']").setValue(addDate("MM.dd.yy"));
        $("[name='name']").setValue("Петров Петр");
        $("[name='phone']").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Неверно введена дат")).shouldBe(visible, ofSeconds(15));
        //$(byText("Личный кабинет")).shouldBe(visible, ofSeconds(5));
    }
}

