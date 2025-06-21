import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

public final class LedgerFormatter implements Function<Ledger.LedgerEntry, String> {
    private final DateTimeFormatter dateFormatter;
    private final DecimalFormat amountFormatter;
    private final TruncatingStringFormatter stringFormatter;
    private final String header;
    private final String entryFormatPattern;

    public LedgerFormatter(Config config) {
        var resource = ResourceBundle.getBundle("LedgerMessages", config.locale());
        dateFormatter = DateTimeFormatter.ofPattern(resource.getString("date.pattern"));
        var symbols = DecimalFormatSymbols.getInstance(config.locale());
        symbols.setCurrencySymbol(config.currency().getSymbol());
        amountFormatter = new DecimalFormat(resource.getString("decimal-format.pattern"), symbols);

        // Load column widths
        var dateColWidth = (Integer) resource.getObject("date.column.width");
        var changeColWidth = (Integer) resource.getObject("change.column.width");
        int descriptionColWidth = (Integer) resource.getObject("description.column.width");

        this.header = "%%-%ds | %%-%ds | %%-%ds"
                .formatted(dateColWidth, descriptionColWidth, changeColWidth)
                .formatted(
                        resource.getString("header.date"),
                        resource.getString("header.description"),
                        resource.getString("header.change"));
        this.entryFormatPattern = "%%-%ds | %%-%ds | %%%ds"
                .formatted(dateColWidth, descriptionColWidth, changeColWidth);

        stringFormatter = new TruncatingStringFormatter(descriptionColWidth, resource.getString("truncation.suffix"));
    }

    public Stream<String> header() {
        return Stream.of(header);
    }

    @Override
    public String apply(Ledger.LedgerEntry transaction) {
        var date = dateFormatter.format(transaction.date());
        var amount = amountFormatter.format(transaction.change());
        var description = stringFormatter.format(transaction.description());
        return entryFormatPattern.formatted(date, description, amount);
    }
}
