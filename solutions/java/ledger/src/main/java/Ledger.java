import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ledger {
    public LedgerEntry createLedgerEntry(String date, String description, int change) {
        return new LedgerEntry(LocalDate.parse(date), description, change);
    }

    public String format(String currencyName, String localeName, LedgerEntry[] entries) {
        var config = new Config(currencyName, localeName);
        var ledgerFormatter = new LedgerFormatter(config);

        var comparator = Comparator
                .comparing(LedgerEntry::change)
                .thenComparing(LedgerEntry::date);

        var body = Arrays.stream(entries)
                .sorted(comparator)
                .map(ledgerFormatter);

        return Stream.concat(ledgerFormatter.header(), body)
                .collect(Collectors.joining("\n"));
    }

    public record LedgerEntry(LocalDate date, String description, double change) {
    }
}
