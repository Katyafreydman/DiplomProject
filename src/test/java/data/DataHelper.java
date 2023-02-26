package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    public static String getApprovedCardNumber() {
        return "4444444444444441";
    }

    public static String getDeclinedCardNumber() {
        return "4444444444444442";
    }

    public static String getInvalidCardNumber() {
        return "4444 4444 4444 4443";
    }

    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getLastMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getNextYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getLastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getEmptyCardNumber() {
        return " ";
    }
    public static String getEmptyMonth() {
        return " ";
    }
    public static String getEmptyYear() {
        return " ";
    }
    public static String getEmptyName() {
        return " ";
    }
    public static String getEmptyCVC() {
        return " ";
    }

    public static String getName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getCVC() {
        return Integer.toString(faker.number().numberBetween(100, 999));
    }

    static Faker faker;

    static {
        faker = new Faker(new Locale("en"));
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String name;
        private String cvc;
    }

    public static CardInfo getCardInfoValidValues() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoCardNumberDeclined() {
        return new CardInfo(getDeclinedCardNumber(), getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoShortNumber() {
        return new CardInfo("4444", getCurrentMonth(), getNextYear(), getName(), getCVC());

    }
    public static CardInfo getCardInfoMonthMore() {
        return new CardInfo(getApprovedCardNumber(), "13", getNextYear(), getName(), getCVC());
    }
    public static CardInfo getCardInfoExpiredLastMonth() {
        return new CardInfo(getApprovedCardNumber(), getLastMonth(), getLastYear(), getName(), getCVC());
    }
    public static CardInfo getCardInfoExpiredLastYear() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getLastYear(), getName(), getCVC());
    }
    public static CardInfo getCardInfoExpired6Years() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), "2028", getName(), getCVC());
    }

    public static CardInfo getCardInfoCyrillicName() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), "Иван Петров", getCVC());
    }

    public static CardInfo getCardInfoSymbolMonth() {
        return new CardInfo(getApprovedCardNumber(), "рпр№поп", getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoSymbolYear() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), "рпр%пло.", getName(), getCVC());
    }

    public static CardInfo getCardInfoSymbolName() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), "3140:??*)", getCVC());
    }


    public static CardInfo getCardInfoCardNumberNull() {
        return new CardInfo("0000000000000000", getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoMonthNull() {
        return new CardInfo(getApprovedCardNumber(), "00", getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoYearNull() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), "00", getName(), getCVC());
    }
    public static CardInfo getCardInfoCVCNull() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getName(), "000");
    }



    public static CardInfo getCardInfoCardNumberLess() {
        return new CardInfo(getInvalidCardNumber(), getCurrentMonth(), getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoCardNumberEmpty() {
        return new CardInfo(" ", getCurrentMonth(), getNextYear(), getName(), getCVC());
    }
    public static CardInfo getCardInfoMonthEmpty() {
        return new CardInfo(getApprovedCardNumber(), " ", getNextYear(), getName(), getCVC());
    }
    public static CardInfo getCardInfoYearEmpty() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), " ", getName(), getCVC());
    }
    public static CardInfo getCardInfoOwnerEmpty() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), " ", getCVC());
    }
    public static CardInfo getCardInfoCVCEmpty() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getNextYear(), getName(), " ");
    }


    public static CardInfo getEmptyFields() {
        return new CardInfo(getEmptyCardNumber(), getEmptyMonth(), getEmptyYear(),
                getEmptyName(), getEmptyCVC());
    }

}


