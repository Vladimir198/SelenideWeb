package ru.netology.sel;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

class CardDeliveryOrderTest {

    String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    String addDate(String format, int dey) {
        LocalDate date = LocalDate.now().plusDays(dey);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    @Test
    void shouldDirectInputOfValues() {
        String meetingDate = addDate("dd.MM.yyyy", 5);
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Нижний Новгород");
        $("[placeholder='Дата встречи']").setValue(deleteString).setValue(meetingDate);
        $("[name='name']").setValue("Петров Петр");
        $("[name='phone']").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, ofSeconds(15));
        $(".notification__content").should(visible, exactTextCaseSensitive("Встреча успешно забронирована на "
        + meetingDate));
    }

    @Test
    void shouldIncorrectDateFormat() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Нижний Новгород");
        $("[placeholder='Дата встречи']").setValue(deleteString).setValue(addDate("MM.dd.yy",4));
        $("[name='name']").setValue("Петров Петр");
        $("[name='phone']").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Неверно введена дата")).shouldBe(visible, ofSeconds(15));
    }

    //Задание 2
    @Test
    void shouldV1() {
        Calendar calendar = new Calendar();
        CityEnter cityEnter = new CityEnter();
        int dey = 21;
        String meetingDate = addDate("dd.MM.yyyy", dey);

        open("http://localhost:9999");
        cityEnter.setCity();
        calendar.calendarEnter(dey);
        $("[name='name']").setValue("Петров Петр");
        $("[name='phone']").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, ofSeconds(15));
        $(".notification__content").should(visible, exactTextCaseSensitive("Встреча успешно забронирована на "
                + meetingDate));
    }
}

