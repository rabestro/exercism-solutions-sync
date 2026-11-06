import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class RateLimiter<K> {
    record TimeFrame(Instant endTime, int count) {
        TimeFrame increase() {
            return new TimeFrame(endTime, count + 1);
        }
    }

    private final ConcurrentMap<K, TimeFrame> frameMap;
    private final int limit;
    private final Duration windowSize;
    private final TimeSource timeSource;

    public RateLimiter(int limit, Duration windowSize, TimeSource timeSource) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timeSource = timeSource;
        this.frameMap = new ConcurrentHashMap<>();
    }

    public boolean allow(K clientId) {
        var now = timeSource.now();
        var allowed = new AtomicBoolean(false);

        frameMap.compute(clientId, (key, current) -> {
            if (current == null || current.endTime().isBefore(now) || now.equals(current.endTime()) ) {
                current = new TimeFrame(now.plus(windowSize), 0);
            }
            if (current.count() < limit) {
                current = current.increase();
                allowed.set(true);
            }
            return current;
        });

        return allowed.get();
    }
}
