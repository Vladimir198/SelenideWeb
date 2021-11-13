package ru.netology.sel;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class Calendar {

    void calendarEnter(int dey) {
        $(".input__icon").click();
        int today = parseInt($(".calendar__day_state_today").getText());
        int monthsEnd = parseInt($$("td").filter(visible).filter(attribute("data-day")).last().text());

        int sum = today + dey;
        if (sum > monthsEnd) {
            $$(".calendar__arrow_direction_right").last().click();
            int w = monthsEnd - today;
            dey -= w;
            $(byText(valueOf(dey))).click();
        }
        else
            $(byText(valueOf(sum))).click();
    }
}
