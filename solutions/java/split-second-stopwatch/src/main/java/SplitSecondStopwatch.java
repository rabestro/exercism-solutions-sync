import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SplitSecondStopwatch {
    enum State {READY, RUNNING, STOPPED}

    private State state = State.READY;
    private Duration totalDuration = Duration.ZERO;
    private Duration currentLap = Duration.ZERO;
    private final List<String> previousLaps = new ArrayList<>();

    private Duration parse(CharSequence text) {
        return Duration.between(LocalTime.MIN, LocalTime.parse(text));
    }

    private String display(Duration duration) {
        return "%02d:%02d:%02d".formatted(
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart()
        );
    }

    public void start() {
        state = switch (state) {
            case READY, STOPPED -> State.RUNNING;
            case RUNNING -> throw new IllegalStateException("cannot start an already running stopwatch");
        };
    }

    public void stop() {
        state = switch (state) {
            case RUNNING -> State.STOPPED;
            case READY, STOPPED -> throw new IllegalStateException("cannot stop a stopwatch that is not running");
        };
    }

    public void reset() {
        if (state != State.STOPPED) {
            throw new IllegalStateException("cannot reset a stopwatch that is not stopped");
        }
        state = State.READY;
        currentLap = Duration.ZERO;
        totalDuration = Duration.ZERO;
        previousLaps.clear();
    }

    public void lap() {
        if (state != State.RUNNING) {
            throw new IllegalStateException("cannot lap a stopwatch that is not running");
        }
        previousLaps.add(display(currentLap));
        currentLap = Duration.ZERO;
    }

    public String state() {
        return state.name().toLowerCase();
    }

    public String currentLap() {
        return display(currentLap);
    }

    public String total() {
        return display(totalDuration);
    }

    public java.util.List<String> previousLaps() {
        return Collections.unmodifiableList(previousLaps);
    }

    public void advanceTime(String timeString) {
        if (state == State.RUNNING) {
            var delta = parse(timeString);
            totalDuration = totalDuration.plus(delta);
            currentLap = currentLap.plus(delta);
        }
    }
}