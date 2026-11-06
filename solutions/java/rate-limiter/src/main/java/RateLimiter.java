import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class RateLimiter<K> {
    record WindowState(Instant windowEnd, int requestCount) {
        WindowState increment() {
            return new WindowState(windowEnd, requestCount + 1);
        }
    }

    private final ConcurrentMap<K, WindowState> windowsByClient;
    private final int limit;
    private final Duration windowDuration;
    private final TimeSource timeSource;

    public RateLimiter(int limit, Duration windowDuration, TimeSource timeSource) {
        Objects.requireNonNull(timeSource);
        Objects.requireNonNull(windowDuration);
        if (limit < 1)
            throw new IllegalArgumentException("The limit must be positive number");
        if (windowDuration.compareTo(Duration.ZERO) <= 0)
            throw new IllegalArgumentException("The window size must be greater than zero");
        this.limit = limit;
        this.timeSource = timeSource;
        this.windowDuration = windowDuration;
        this.windowsByClient = new ConcurrentHashMap<>();
    }

    public boolean allow(K clientId) {
        var now = timeSource.now();
        var allowed = new AtomicBoolean(false);

        windowsByClient.compute(clientId, (key, current) -> {
            if (current == null || current.windowEnd().isBefore(now) || now.equals(current.windowEnd()) ) {
                current = new WindowState(now.plus(windowDuration), 0);
            }
            if (current.requestCount() < limit) {
                current = current.increment();
                allowed.set(true);
            }
            return current;
        });

        return allowed.get();
    }
}
