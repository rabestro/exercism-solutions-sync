import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

record ParallelLetterFrequency(String[] texts) {
    
    Map<Character, Integer> countLetters() {
        return Arrays.stream(texts)
                .parallel()
                .flatMapToInt(String::chars)
                .filter(Character::isLetter)
                .map(Character::toLowerCase)
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
    }
}
