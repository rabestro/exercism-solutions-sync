import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

public final class LedgerFormatter implements Function<Ledger.LedgerEntry, String> {
    private static final int maxWidth = 25;
    private final DateTimeFormatter dateFormatter;
    private final DecimalFormat decimalFormat;
    private final ResourceBundle resource;

    public LedgerFormatter(Config config) {
        resource = ResourceBundle.getBundle("LedgerMessages", config.locale());
        var symbols = DecimalFormatSymbols.getInstance(config.locale());
        symbols.setCurrencySymbol(config.currency().getSymbol());
        decimalFormat = new DecimalFormat(resource.getString("decimal-format.pattern"), symbols);
        dateFormatter = DateTimeFormatter.ofPattern(resource.getString("date.pattern"));
    }

    public Stream<String> header() {
        var header = "%-10s | %-25s | %-13s".formatted(
                resource.getString("header.date"),
                resource.getString("header.description"),
                resource.getString("header.change")
        );
        return Stream.of(header);
    }

    @Override
    public String apply(Ledger.LedgerEntry transaction) {
        var date = transaction.date().format(dateFormatter);
        var description = truncateToMaxWidth(transaction.description());
        var amount = decimalFormat.format(transaction.change());
        return "%-10s | %-25s | %13s".formatted(date, description, amount);
    }

    private String truncateToMaxWidth(String s) {
        return s.length() <= maxWidth ? s : s.substring(0, maxWidth - 3) + "...";
    }
}
