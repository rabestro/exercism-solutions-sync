import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter<K> {
    record TimeFrame(Instant endTime, int count) {
        TimeFrame increase() {
            return new TimeFrame(endTime, count + 1);
        }
    }

    private final Map<K, TimeFrame> frameMap;
    private final int limit;
    private final Duration windowSize;
    private final TimeSource timeSource;

    public RateLimiter(int limit, Duration windowSize, TimeSource timeSource) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timeSource = timeSource;
        this.frameMap = new HashMap<>();
    }

    public boolean allow(K clientId) {
        var timeFrame = frameMap.getOrDefault(clientId, new TimeFrame(timeSource.now().plus(windowSize), 0));
        if (timeFrame.endTime().isBefore(timeSource.now()) || timeSource.now().equals(timeFrame.endTime())) {
            timeFrame = new TimeFrame(timeSource.now().plus(windowSize), 0);
        }
        if (timeFrame.count() == limit) return false;
        frameMap.put(clientId, timeFrame.increase());
        return true;
    }
}
