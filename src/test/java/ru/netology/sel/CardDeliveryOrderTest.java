package ru.netology.sel;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryOrderTest {
    //git add -f artifacts/app-card-delivery.jar
    //java -jar ./artifacts/app-card-delivery.jar

    static String addData () {
        LocalDate data = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return data.format(formatter);
    }
    @Test
    void shouldDirectInputOfValues() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Нижний Новгород");
        $("[placeholder='Дата встречи']").setValue(addData());
        $("[name='name']").setValue("Петров Птр");
        $("[name='phone']").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
//        $(byText("Личный кабинет")).shouldBe(visible, Duration.ofMillis(5000));
    }

//    @Test
//    void shouldRegisterByAccountNumberVisibilityChange() {
//        open("http://localhost:9999");
//        $$(".tab-item").find(exactText("По номеру счёта")).click();
//        $$("[name='number']").last().setValue("4055 0100 0123 4613 8564");
//        $$("[name='phone']").last().setValue("+792000000000");
//        $$("button").find(exactText("Продолжить")).click();
//        $(withText("Успешно! авторизация")).shouldBe(visible, Duration.ofSeconds(5));
//        $(byText("Личный кабинет")).shouldBe(visible, Duration.ofSeconds(5));
//    }
}

