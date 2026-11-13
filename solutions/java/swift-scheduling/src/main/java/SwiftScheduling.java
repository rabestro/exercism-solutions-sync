import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;

public class SwiftScheduling {
    private static final LocalTime WORKDAY_START = LocalTime.of(8, 0);
    private static final LocalTime ASAP_CUTOFF = LocalTime.of(13, 0);
    private static final LocalTime WORKDAY_END = LocalTime.of(17, 0);
    private static final LocalTime WEEKEND_DEADLINE = LocalTime.of(20, 0);

    public static LocalDateTime convertToDeliveryDate(LocalDateTime meetingStart, String description) {
        return switch (description) {
            case "NOW" -> meetingStart.plusHours(2);
            case "ASAP" -> asap(meetingStart);
            case "EOW" -> endOfWeek(meetingStart);
            case String d when d.endsWith("M") ->
                    nthMonth(meetingStart, Integer.parseInt(d.substring(0, d.length() - 1)));
            case String d when d.startsWith("Q") ->
                    nthQuarter(meetingStart, Integer.parseInt(d.substring(1)));
            default -> throw new IllegalArgumentException("Unknown description: " + description);
        };
    }

    private static LocalDateTime asap(LocalDateTime meetingStart) {
        var day = meetingStart.toLocalDate();
        return meetingStart.toLocalTime().isBefore(ASAP_CUTOFF)
                ? day.atTime(WORKDAY_END)
                : day.plusDays(1).atTime(ASAP_CUTOFF);
    }

    private static LocalDateTime endOfWeek(LocalDateTime meetingStart) {
        LocalDate day = meetingStart.toLocalDate();
        return switch (day.getDayOfWeek()) {
            case MONDAY, TUESDAY, WEDNESDAY ->
                    day.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).atTime(WORKDAY_END);
            default ->
                    day.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).atTime(WEEKEND_DEADLINE);
        };
    }

    private static LocalDateTime nthMonth(LocalDateTime meetingStart, int month) {
        int year = meetingStart.getYear() + (meetingStart.getMonthValue() < month ? 0 : 1);
        return firstWorkday(YearMonth.of(year, month)).atTime(WORKDAY_START);
    }

    private static LocalDateTime nthQuarter(LocalDateTime meetingStart, int quarter) {
        int currentQuarter = meetingStart.get(IsoFields.QUARTER_OF_YEAR);
        int year = meetingStart.getYear() + (currentQuarter <= quarter ? 0 : 1);
        return lastWorkday(YearMonth.of(year, quarter * 3)).atTime(WORKDAY_START);
    }

    private static LocalDate firstWorkday(YearMonth month) {
        var first = month.atDay(1);
        return switch (first.getDayOfWeek()) {
            case SATURDAY -> first.plusDays(2);
            case SUNDAY -> first.plusDays(1);
            default -> first;
        };
    }

    private static LocalDate lastWorkday(YearMonth month) {
        var last = month.atEndOfMonth();
        return switch (last.getDayOfWeek()) {
            case SATURDAY -> last.minusDays(1);
            case SUNDAY -> last.minusDays(2);
            default -> last;
        };
    }
}