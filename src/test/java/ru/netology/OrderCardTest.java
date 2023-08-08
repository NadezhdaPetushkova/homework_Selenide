package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class OrderCardTest {
    @BeforeEach
    public void openLocalhost() {
        open("http://localhost:9999");
    }

    @Test
    void successForm() {
        $("[data-test-id=name] input").val("Петушкова Надежда");
        $("[data-test-id=phone] input").val("+79221234567");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void successFormWithDash() {
        $("[data-test-id=name] input").val("Иванова Анна-Мария");
        $("[data-test-id=phone] input").val("+79220000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void ifEnglishName() {
        $("[data-test-id=name] input").val("Sokolov Andrei");
        $("[data-test-id=phone] input").val("+79220000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void ifNameWithNumber() {
        $("[data-test-id=name] input").val("Sokolov2");
        $("[data-test-id=phone] input").val("+79220000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void ifNameWithSymbol() {
        $("[data-test-id=name] input").val("Sokolov #");
        $("[data-test-id=phone] input").val("+79220000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void ifNameEmpty() {
        $("[data-test-id=phone] input").val("+79220000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void ifPhoneNumberStartEight() {
        $("[data-test-id=name] input").val("Екатерина Ильина");
        $("[data-test-id=phone] input").val("89220000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void ifPhoneNumberLessElevenSymbol() {
        $("[data-test-id=name] input").val("Екатерина Ильина");
        $("[data-test-id=phone] input").val("8922123456");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void ifPhoneNumberMoreElevenSymbol() {
        $("[data-test-id=name] input").val("Екатерина Ильина");
        $("[data-test-id=phone] input").val("892212345678");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void ifPhoneNumberWithSpace() {
        $("[data-test-id=name] input").val("Екатерина Ильина");
        $("[data-test-id=phone] input").val("+7 929 0000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void ifPhoneNumberWithLetter() {
        $("[data-test-id=name] input").val("Екатерина Ильина");
        $("[data-test-id=phone] input").val("+7V9290000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void ifPhoneNumberWithSymbol() {
        $("[data-test-id=name] input").val("Екатерина Ильина");
        $("[data-test-id=phone] input").val("+7(999)1234567");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void ifPhoneNumberEmpty() {
        $("[data-test-id=name] input").val("Панова Наталья");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
    @Test
    void notAgreement() {
        $("[data-test-id=name] input").val("Елисеева Анастасия");
        $("[data-test-id=phone] input").val("+79120000000");
        $(By.className("button")).click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldBe(Condition.visible);
    }
}