import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import static java.util.Objects.requireNonNull;

public class TruncatingStringFormatter extends Format {

    private final int maxWidth;
    private final String truncationSuffix;

    public TruncatingStringFormatter(int maxWidth, String truncationSuffix) {
        if (maxWidth < 0) {
            throw new IllegalArgumentException("Max width cannot be negative.");
        }
        this.maxWidth = maxWidth;
        this.truncationSuffix = requireNonNull(truncationSuffix, "Truncation suffix cannot be null.");
    }

    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (!(obj instanceof String s)) {
            throw new IllegalArgumentException("TruncatingStringFormat can only format String objects.");
        }

        // Record the starting position for FieldPosition tracking.
        // FieldPosition is complex; for simple formatters, you often update it
        // to cover the entire formatted output or ignore specific field tracking.
        int initialLength = toAppendTo.length();

        if (s.length() <= this.maxWidth) {
            toAppendTo.append(s);
        } else {
            // s.length() > this.maxWidth, so truncation is needed.
            if (this.maxWidth < this.truncationSuffix.length()) {
                // Not enough space to include the suffix; just truncate the original string.
                // If maxWidth is 0, this appends an empty string.
                toAppendTo.append(s, 0, this.maxWidth);
            } else {
                // Enough space for the suffix and some part of the original string.
                toAppendTo.append(s, 0, this.maxWidth - this.truncationSuffix.length());
                toAppendTo.append(this.truncationSuffix);
            }
        }

        // Example of updating FieldPosition to mark the whole output (if no specific field is targeted)
        // A real implementation might have specific Format.Field constants if needed.
        if (pos.getFieldAttribute() == null) { // Or check for a specific field you care about
            pos.setBeginIndex(initialLength);
            pos.setEndIndex(toAppendTo.length());
        }
        // If you had specific fields (e.g., TruncatingStringFormat.ORIGINAL_PART, TruncatingStringFormat.SUFFIX_PART)
        // you would set pos.setBeginIndex/setEndIndex more precisely for those fields.

        return toAppendTo;
    }

    @Override
    public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException("Parsing not supported by TruncatingStringFormat.");
    }

    // Convenience method (optional, as Format.format(Object) already exists)
    public String format(String s) {
        return format((Object) s);
    }
}