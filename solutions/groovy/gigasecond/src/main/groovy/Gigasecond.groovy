import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

class Gigasecond {
    static def GIGA_SECOND = Duration.ofSeconds(1_000_000_000)

    static add(LocalDateTime moment) {
        return moment + GIGA_SECOND
    }

    static add(LocalDate moment) {
        return add(moment.atStartOfDay())
    }
}
