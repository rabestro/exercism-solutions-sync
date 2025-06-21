import java.util.function.UnaryOperator;

import static java.util.Objects.requireNonNull;

public class StringTruncator implements UnaryOperator<String> {
    private final int maxWidth;
    private final String truncationSuffix;

    public StringTruncator(int maxWidth, String truncationSuffix) {
        this.truncationSuffix = requireNonNull(truncationSuffix, "Truncation suffix cannot be null.");
        if (maxWidth <= truncationSuffix.length()) {
            throw new IllegalArgumentException("Max width must be greater than truncation suffix length.");
        }
        this.maxWidth = maxWidth;
    }

    @Override
    public String apply(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() <= maxWidth) {
            return s;
        }
        return s.substring(0, maxWidth - truncationSuffix.length()) + truncationSuffix;
    }
}