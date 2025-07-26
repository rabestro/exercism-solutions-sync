import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {
    String verse(int verseNumber) {
        var i = verseNumber - 1;
        var days = Days.values();
        var song = new StringJoiner(" ",
                "On the " + days[i].name().toLowerCase() + " day of Christmas my true love gave to me: ",
                ".\n");

        for (; i >= 0; --i) {
            song.add(days[i].getGift());
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

    enum Days {
        FIRST("a Partridge in a Pear Tree"),
        SECOND("two Turtle Doves, and"),
        THIRD("three French Hens,"),
        FOURTH("four Calling Birds,"),
        FIFTH("five Gold Rings,"),
        SIXTH("six Geese-a-Laying,"),
        SEVENTH("seven Swans-a-Swimming,"),
        EIGHTH("eight Maids-a-Milking,"),
        NINTH("nine Ladies Dancing,"),
        TENTH("ten Lords-a-Leaping,"),
        ELEVENTH("eleven Pipers Piping,"),
        TWELFTH("twelve Drummers Drumming,");

        private final String gift;

        Days(String gift) {
            this.gift = gift;
        }

        String getGift() {
            return gift;
        }
    }
}

