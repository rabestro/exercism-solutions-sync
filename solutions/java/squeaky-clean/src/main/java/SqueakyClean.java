import java.util.OptionalInt;

class SqueakyClean {
    static String clean(String identifier) {
        return identifier
                .codePoints()
                .mapToObj(new CodePointConverter())
                .filter(OptionalInt::isPresent)
                .mapToInt(OptionalInt::getAsInt)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }
}
