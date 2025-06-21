import java.util.Objects;
import java.util.function.UnaryOperator;

public class StringTruncator implements UnaryOperator<String> {
    private final int maxWidth;
    private final String truncationSuffix;

    public StringTruncator(int maxWidth, String truncationSuffix) {
        this.truncationSuffix = Objects.requireNonNull(truncationSuffix, "Truncation suffix cannot be null.");
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
        if (s.length() <= this.maxWidth) {
            return s;
        }
        return s.substring(0, this.maxWidth - this.truncationSuffix.length()) + this.truncationSuffix;
    }
}