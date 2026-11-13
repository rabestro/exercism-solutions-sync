import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class SwiftScheduling {
    private static final TemporalAdjuster nextWorkday = temporal -> {
        LocalDate result = (LocalDate) temporal;
        do {
            result = result.plusDays(1);
        } while (result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY);
        return result;
    };

    private static final TemporalAdjuster nextOrSameWorkday = temporal -> {
        LocalDate result = (LocalDate) temporal;
        while (result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY) {
            result = result.plusDays(1);
        }
        return result;
    };

    private static final TemporalAdjuster previousOrSameWorkday = temporal -> {
        LocalDate result = (LocalDate) temporal;
        while (result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY) {
            result = result.minusDays(1);
        }
        return result;
    };

    public static LocalDateTime convertToDeliveryDate(LocalDateTime meetingStart, String description) {
        return switch (description) {
            case "NOW" -> meetingStart.plusHours(2);
            case "ASAP" -> asap(meetingStart);
            case "EOW" -> eow(meetingStart);
            default -> {
                if (description.endsWith("M")) {
                    var n = Integer.parseInt(description.substring(0, description.length() - 1));
                    var year = meetingStart.getYear() + (meetingStart.getMonthValue() < n ? 0 : 1);
                    yield YearMonth.of(year, n)
                            .atDay(1)
                            .with(nextOrSameWorkday)
                            .atTime(8, 0);
                }
                var n = Integer.parseInt(description.substring(1));
                var q = meetingStart.get(IsoFields.QUARTER_OF_YEAR);
                var m = n * 3;
                var y = meetingStart.getYear() + (q <= n ? 0 : 1);
                yield YearMonth.of(y, m)
                        .atEndOfMonth()
                        .with(previousOrSameWorkday)
                        .atTime(8, 0);
            }
        };
    }

    private static LocalDateTime asap(LocalDateTime meetingStart) {
        return meetingStart.toLocalTime().isBefore(LocalTime.of(13, 0))
                ? meetingStart.withHour(17).withMinute(0)
                : meetingStart.plusDays(1).withHour(13).withMinute(0);
    }

    private static LocalDateTime eow(LocalDateTime meetingStart) {
        var dayOfWeek = meetingStart.getDayOfWeek();
        return switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY -> meetingStart
                    .with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).withHour(17).withMinute(0);
            default -> meetingStart
                    .with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).withHour(20).withMinute(0);

        };
    }

}
