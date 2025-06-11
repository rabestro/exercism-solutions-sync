import java.util.regex.Pattern;

class SqueakyClean {
    static String clean(String identifier) {
        var sb = new StringBuilder();
        for (int i = 0, n = identifier.length(); i < n; ++i) {
            char symbol = identifier.charAt(i);
            if (symbol >= 'α' && symbol <= 'ω') {
                continue;
            }
            if (symbol == '-') {
                symbol = Character.toUpperCase(identifier.charAt(++i));
            }
            if (Character.isISOControl(symbol)) {
                sb.append("CTRL");
            } else if (Character.isSpaceChar(symbol)) {
                sb.append('_');
            } else if (Character.isLetter(symbol)) {
                sb.append(symbol);
            }
        }
        return sb.toString();
    }
}
