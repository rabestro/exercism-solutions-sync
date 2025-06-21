import java.util.ListResourceBundle;

public class LedgerMessages_nl_NL extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"header.date", "Datum"},
                {"header.description", "Omschrijving"},
                {"header.change", "Verandering"},
                {"date.pattern", "dd/MM/yyyy"},
                {"decimal-format.pattern", "¤ #,##0.00 ;¤ -#,##0.00 "},
        };
    }
}