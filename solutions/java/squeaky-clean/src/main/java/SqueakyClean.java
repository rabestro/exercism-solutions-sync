class SqueakyClean {
    static String clean(String identifier) {
        return kebabCase(identifier)
                .replace(' ', '_')
                .replaceAll("\\p{Cntrl}", "CTRL")
                .replaceAll("[\\p{InGreek}&&\\p{javaLowerCase}]", "")
                .replaceAll("[\\P{IsLetter}&&[^_]]", "");
    }

    private static String kebabCase(String identifier) {
        var sb = new StringBuilder(identifier);
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '-') {
                sb.deleteCharAt(i);
                sb.replace(i, i+1, String.valueOf(Character.toUpperCase(sb.charAt(i))));
            }
        }
        return sb.toString();
    }
}
