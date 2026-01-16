class SqueakyClean {
    static String clean(String identifier) {
        return identifier
                .codePoints()
                .map(new SqueakyCodePointConverter())
                .filter(c -> c != Character.MIN_VALUE)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }
}
