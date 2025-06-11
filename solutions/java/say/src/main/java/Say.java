import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

public final class Say {
    public static final long MAXIMUM_PRONOUNCEABLE_NUMBER = 999_999_999_999L;
    private static final String[] numbers = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
            "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };
    private static final Map<Long, String> MAGNITUDES = Map.of(
            1_000_000_000L, " billion",
            1_000_000L, " million",
            1_000L, " thousand",
            1L, "");

    public String say(long number) {
        validateNumber(number);

        if (number == 0) {
            return numbers[0];
        }
        return MAGNITUDES.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(reverseOrder()))
                .map(e -> translate((int) (number % (e.getKey() * 1000) / e.getKey()), e.getValue()))
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.joining(" "));
    }

    private void validateNumber(long number) {
        if (number < 0 || number > MAXIMUM_PRONOUNCEABLE_NUMBER) {
            throw new IllegalArgumentException();
        }
    }

    private String translate(int number, String unit) {
        if (number < 1) {
            return "";
        }
        if (number < 21) {
            return numbers[number] + unit;
        }
        if (number < 100) {
            return tens(number) + lastDigit(number) + unit;
        }
        var units = translate(number % 100, "");
        return translate(number / 100, " hundred") + (units.isBlank() ? "" : " " + units) + unit;
    }

    private String tens(int number) {
        return numbers[18 + number / 10];
    }

    private String lastDigit(int number) {
        var lastDigit = number % 10;
        return lastDigit == 0 ? "" : "-" + numbers[lastDigit];
    }
}
