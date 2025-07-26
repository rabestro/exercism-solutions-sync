import java.util.StringJoiner;
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
        var i = verseNumber - 1;
        var song = new StringJoiner(" ",
            "On the " + DAYS[i] + " day of Christmas my true love gave to me: ",
            ".\n");

        for (; i >= 0; --i) {
            song.add(GIFTS[i]);
        }
        return song.toString();
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

