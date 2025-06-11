import java.util.OptionalInt;
import java.util.function.IntFunction;

public class CodePointConverter implements IntFunction<OptionalInt> {
    private boolean isUpperCase;

    @Override
    public OptionalInt apply(int codePoint) {
        return switch (codePoint) {
            case '0' -> OptionalInt.of('o');
            case '1' -> OptionalInt.of('l');
            case '3' -> OptionalInt.of('e');
            case '4' -> OptionalInt.of('a');
            case '7' -> OptionalInt.of('t');
            case ' ' -> OptionalInt.of('_');
            case '-' -> {
                isUpperCase = true;
                yield OptionalInt.empty();
            }
            default -> {
                if (!Character.isLetter(codePoint)) {
                    yield OptionalInt.empty();
                }
                if (isUpperCase) {
                    isUpperCase = false;
                    yield OptionalInt.of(Character.toUpperCase(codePoint));
                }
                yield OptionalInt.of(codePoint);
            }
        };
    }
}
