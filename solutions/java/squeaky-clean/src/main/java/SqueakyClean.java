import java.util.regex.Pattern;

class SqueakyClean {
    private static final Pattern KEBAB_CASE = Pattern.compile("-(\\D)");

    static String clean(String identifier) {
        return kebabCase(identifier)
                .replace(' ', '_')
                .replaceAll("\\p{Cntrl}", "CTRL")
                .replaceAll("[\\p{InGreek}&&\\p{javaLowerCase}]", "")
                .replaceAll("[\\P{IsLetter}&&[^_]]", "");
    }

    private static String kebabCase(String identifier) {
        var matcher = KEBAB_CASE.matcher(identifier);
        return matcher.find()
                ? matcher.replaceAll(matcher.group(1).toUpperCase())
                : identifier;
    }
}
