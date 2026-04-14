class SqueakyClean {
    static String clean(String identifier) {
        var sb = new StringBuilder();
        var isUpperCase = false;

        for (int codePoint : identifier.codePoints().toArray()) {
            switch (codePoint) {
                case '0' -> sb.append('o');
                case '1' -> sb.append('l');
                case '3' -> sb.append('e');
                case '4' -> sb.append('a');
                case '7' -> sb.append('t');
                case ' ' -> sb.append('_');
                case '-' -> isUpperCase = true;
                default -> {
                    if (Character.isLetter(codePoint)) {
                        sb.appendCodePoint(isUpperCase ? Character.toUpperCase(codePoint) : codePoint);
                        isUpperCase = false;
                    }
                }
            }
        }
        return sb.toString();
    }
}