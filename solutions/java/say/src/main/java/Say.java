import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Say {
    private static final String[] numbers = new String[]{
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
            "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    public String say(long number) {
        if (number < 0 || number > 999_999_999_999L) {
            throw new IllegalArgumentException();
        }
        if (number == 0) {
            return numbers[0];
        }
        final var units = List.of("billion", "million", "thousand", "").iterator();

        return LongStream.of(number / 1_000_000_000, number % 1_000_000_000 / 1_000_000,
                number % 1_000_000 / 1_000, number % 1_000)
                .mapToObj(i -> say((int) i, units.next()))
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.joining(" "));
    }

    private String say(int number, String unit) {
        if (number == 0) {
            return "";
        }
        var sj = new StringJoiner(" ");
        if (number >= 100) {
            sj.add(say(number / 100, "hundred"));
        }
        number %= 100;
        if (number > 20) {
            sj.add(numbers[18 + number / 10] + "-" + numbers[number % 10]);
        } else if (number > 0) {
            sj.add(numbers[number]);
        }
        return unit.isBlank() ? sj.toString() : sj.add(unit).toString();
    }

}
