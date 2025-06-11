import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

public class Say {
    private static final String[] numbers = new String[]{
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
            "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };
    private static final Map<Long, String> units = Map.of(
            1_000_000_000L, " billion", 1_000_000L, " million", 1_000L, " thousand", 1L, "");

    public String say(long number) {
        if (number < 0 || number > 999_999_999_999L) {
            throw new IllegalArgumentException();
        }
        if (number == 0) {
            return numbers[0];
        }
        return units.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(reverseOrder()))
                .map(e -> translate((int) (number % (e.getKey() * 1000) / e.getKey()), e.getValue()))
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.joining(" "));
    }

    private String translate(int number, String unit) {
        if (number < 1) {
            return "";
        }
        if (number < 21) {
            return numbers[number] + unit;
        }
        if (number < 100) {
            final var units = number % 10;
            return numbers[18 + number / 10] + (units == 0 ? "" : "-" + numbers[units]) + unit;
        }
        final var units = translate(number % 100, "");
        return translate(number / 100, " hundred") + (units.isBlank() ? "" : " " + units) + unit;
    }

}
