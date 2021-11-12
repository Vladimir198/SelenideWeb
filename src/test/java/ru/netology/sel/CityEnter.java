package ru.netology.sel;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CityEnter {

    void setCity() {
        $("[placeholder='Город']").setValue("Ни");
        $$(".menu-item__control").find(exactText("Нижний Новгород")).click();
    }
}
