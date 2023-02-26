package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardPaymentTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @BeforeEach
    void clearDataBase() {
        SQLHelper.cleanDataBase();
    }


    @Test
    public void shouldValidValues() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoValidValues());
        cardPayment.successfulPayment();
        var paymentStatus = SQLHelper.getStatusCardPayment();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    public void shouldIfCardNumberDeclined() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoCardNumberDeclined());
        cardPayment.errorPayment();
        var paymentStatus = SQLHelper.getStatusCardPayment();
        assertEquals("DECLINED", paymentStatus);
    }

    // 1 Отправка формы, с номером несуществующей карты.
    @Test
    public void shouldNotConfirmPaymentWithFakeCard() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoCardNumberDeclined());
        cardPayment.errorPayment();
        var paymentStatus = SQLHelper.getStatusCardPayment();
        assertEquals("DECLINED", paymentStatus);

    }


    // 2 Отправка формы, с коротким номером карты.

    @Test
    public void shouldIfCardShortNumber() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoShortNumber());
        cardPayment.invalidFormatField();
    }


    // 3 Отправка формы, невалидный месяц.
    @Test
    public void shouldIfMonthMore() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoMonthMore());
        cardPayment.expiredLastMonth();
    }

    // 4 Отправка формы, прошлый месяц

    @Test
    public void shouldIfExpiredLastMonth() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoExpiredLastMonth());
        cardPayment.expiredLastMonth();
    }

    // 5 Отправка формы,с истекшим сроком действия карты в прошлом году .
    @Test
    public void shouldIfExpiredLastYear() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoExpiredLastYear());
        cardPayment.expiredLastYear();
    }

    //  6 Отправка формы,с с истекшим сроком действия карты на 6 лет отстающей от текущей.
    @Test
    public void shouldIfExpired6Years() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoExpired6Years());
        cardPayment.expired6Years();
    }

    //  7 Отправка формы,невалидный владелец, данные на кириллице.
    @Test
    public void shouldIfCyrillicName() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoCyrillicName());
        cardPayment.invalidFormatField();
    }

//  8 Отправка формы,буквенное значение со спецсимволами в поле "Месяц".

    @Test
    public void shouldIfSymbolMonth() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoSymbolMonth());
        cardPayment.invalidMonth();
    }

    //  9 Отправка формы,буквенное значение со спецсимволами в поле "ГОД".
    @Test
    public void shouldIfSymbolYear() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoSymbolYear());
        cardPayment.invalidYear();
    }

    //  10 Отправка формы,буквенное значение со спецсимволами в поле "Владелец".
    @Test
    public void shouldIfSymbolName() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoSymbolName());
        cardPayment.invalidFormatField();
    }


    // Отправка формы "Купить" с нулями в цифровых полях.

    // 1 Отправка формы "Купить" с нулевым значением в поле "Номер карты".
    @Test
    public void shouldIfCardNumberNull() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoCardNumberNull());
        cardPayment.invalidFormatField();
    }

    // 2 Отправка формы "Купить" с нулевым значением в поле "Месяц".
    @Test
    public void shouldIfMonthNull() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoMonthNull());
        cardPayment.invalidFormatField();
    }

    // 3 Отправка формы "Купить" с нулевым значением в поле "Год".
    @Test
    public void shouldIfYearNull() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoYearNull());
        cardPayment.invalidFormatField();
    }

    // 4 Отправка формы "Купить" с нулевым значением в поле "CVC"
    @Test
    public void shouldIfCVCNull() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoCVCNull());
        cardPayment.invalidFormatField();
    }

    //  Отправка формы  с пустыми полями

    //  1 Отправка пустой формы
    @Test
    public void shouldIfEmptyForm() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getEmptyFields());
        cardPayment.invalidFormatField();
        cardPayment.requiredField();
    }

    // 2 Отправка формы с пустым полем "Номер карты"
    @Test
    public void shouldIfCardNumberEmpty() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoCardNumberEmpty());
        cardPayment.invalidFormatField();
    }
    // 3 Отправка формы с пустым полем "Месяц"
    @Test
    public void shouldIfMonthEmpty() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoMonthEmpty());
        cardPayment.invalidFormatField();
    }
    // 4 Отправка формы с пустым полем "Год"
    @Test
    public void shouldIfYearEmpty() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoYearEmpty());
        cardPayment.invalidFormatField();
    }
    // 5 Отправка формы с пустым полем "Владелец"
    @Test
    public void shouldIfOwnerEmpty() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoOwnerEmpty());
        cardPayment.requiredField();
    }
    // 6 Отправка формы с пустым полем "CVC"
    @Test
    public void shouldIfCVCEmpty() {
        var startPage = new StartPage();
        var cardPayment = startPage.cardPayment();
        cardPayment.fillingPaymentForm(DataHelper.getCardInfoCVCEmpty());
        cardPayment.invalidFormatField();
    }

}