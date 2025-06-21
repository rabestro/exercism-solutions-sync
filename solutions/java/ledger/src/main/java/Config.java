import java.util.Currency;
import java.util.Locale;
import java.util.Set;

public record Config(Currency currency, Locale locale) {
    private static final Set<String> CURRENCIES = Set.of("USD", "EUR");
    private static final Set<String> LOCALES = Set.of("en-US", "nl-NL");

    public Config(String currencyName, String localeName) {
        this(getCurrency(currencyName), getLocale(localeName));
    }

    private static Currency getCurrency(String currencyName) {
        if (!CURRENCIES.contains(currencyName)) {
            throw new IllegalArgumentException("Invalid currency");
        }
        return Currency.getInstance(currencyName);
    }

    private static Locale getLocale(String localeName) {
        if (!LOCALES.contains(localeName)) {
            throw new IllegalArgumentException("Invalid locale");
        }
        return Locale.forLanguageTag(localeName);
    }
}
