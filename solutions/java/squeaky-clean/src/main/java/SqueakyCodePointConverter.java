import java.util.function.IntUnaryOperator;

public class SqueakyCodePointConverter implements IntUnaryOperator {
    private boolean isUpperCase;

    @Override
    public int applyAsInt(int codePoint) {
        return switch (codePoint) {
            case '0' -> 'o';
            case '1' -> 'l';
            case '3' -> 'e';
            case '4' -> 'a';
            case '7' -> 't';
            case ' ' -> '_';
            case '-' -> {
                isUpperCase = true;
                yield Character.MIN_VALUE;
            }
            default -> {
                if (!Character.isLetter(codePoint)) {
                    yield Character.MIN_VALUE;
                }
                if (isUpperCase) {
                    isUpperCase = false;
                    yield Character.toUpperCase(codePoint);
                }
                yield codePoint;
            }
        };
    }
}
