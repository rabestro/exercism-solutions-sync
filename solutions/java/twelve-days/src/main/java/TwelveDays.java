import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {
    private static final String[] DAYS = {
        "first", "second", "third", "fourth", "fifth", "sixth",
        "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"
    };
    private static final String[] GIFTS = {
        "a Partridge in a Pear Tree",
        "two Turtle Doves, and",
        "three French Hens,",
        "four Calling Birds,",
        "five Gold Rings,",
        "six Geese-a-Laying,",
        "seven Swans-a-Swimming,",
        "eight Maids-a-Milking,",
        "nine Ladies Dancing,",
        "ten Lords-a-Leaping,",
        "eleven Pipers Piping,",
        "twelve Drummers Drumming,"
    };

    String verse(int verseNumber) {
        var dayIndex = verseNumber - 1;
        var gifts = IntStream.rangeClosed(0, dayIndex)
            .map(i -> dayIndex - i) // Iterate backwards from dayIndex down to 0
            .mapToObj(i -> GIFTS[i])
            .collect(Collectors.joining(" "));

        return "On the %s day of Christmas my true love gave to me: %s.\n"
            .formatted(DAYS[dayIndex], gifts);
    }

    String verses(int startVerse, int endVerse) {
        return IntStream
            .rangeClosed(startVerse, endVerse)
            .mapToObj(this::verse)
            .collect(Collectors.joining("\n"));
    }

    String sing() {
        return verses(1, 12);
    }
}

