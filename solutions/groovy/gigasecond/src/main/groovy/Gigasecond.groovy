import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

class Gigasecond {
    static def GIGA_SECOND = Duration.ofSeconds(1_000_000_000)

    static add(LocalDateTime moment) {
        moment + GIGA_SECOND
    }

    static add(LocalDate moment) {
        add(moment.atStartOfDay())
    }
}
