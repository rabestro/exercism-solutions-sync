import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

class SalonCalendar {
    public LocalDate standingAppointment(int year, Month month, int occurrence, DayOfWeek dayOfWeek) {
        return YearMonth.of(year, month)
                .atDay(1)
                .with(TemporalAdjusters.dayOfWeekInMonth(occurrence, dayOfWeek));
    }

    public LocalDate lastAppointment(int year, Month month, DayOfWeek dayOfWeek) {
        return YearMonth.of(year, month)
                .atEndOfMonth()
                .with(TemporalAdjusters.previousOrSame(dayOfWeek));
    }

    public LocalDate nextAvailable(LocalDate from, DayOfWeek dayOfWeek) {
        return from.with(TemporalAdjusters.nextOrSame(dayOfWeek));
    }

    public LocalDate bookkeepingDay(int year, Month month) {
        return YearMonth.of(year, month)
                .atEndOfMonth()
                .with(precedingWorkday());
    }

    public LocalDate payday(int year, Month month) {
        return YearMonth.of(year, month)
                .atDay(15)
                .with(precedingWorkday());
    }

    public static TemporalAdjuster precedingWorkday() {
        return TemporalAdjusters.ofDateAdjuster(date -> switch (date.getDayOfWeek()) {
            case SATURDAY -> date.minusDays(1);
            case SUNDAY -> date.minusDays(2);
            default -> date;
        });
    }
}
