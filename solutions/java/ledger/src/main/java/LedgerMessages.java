import java.util.ListResourceBundle;

public class LedgerMessages extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                // Original String resources
                {"header.date", "Date"},
                {"header.description", "Description"},
                {"header.change", "Change"},
                {"date.pattern", "MM/dd/yyyy"},
                {"decimal-format.pattern", "¤#,##0.00 ;(¤#,##0.00)"},
                {"truncation.suffix", "..."},

                // Column widths stored directly as Integers
                {"date.column.width", 10},
                {"description.column.width", 25},
                {"change.column.width", 13}
        };
    }
}