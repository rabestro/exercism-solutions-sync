import java.util.stream.Collectors;
import java.util.stream.IntStream;

record Proverb(String[] words) {
    String recite() {
        return IntStream.range(0, words.length)
                .mapToObj(this::line)
                .collect(Collectors.joining("\n"));
    }

    private String line(int i) {
        return i == words.length - 1
            ? "And all for the want of a " + words[0] + "."
            : "For want of a " + words[i] + " the " + words[i + 1] + " was lost.";
    }
}
