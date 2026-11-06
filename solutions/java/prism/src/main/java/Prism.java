import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.lang.Math.*;

public class Prism {

    private static final double DELTA = 0.01;

    public record LaserInfo(double x, double y, double angle) {
    }

    public record PrismInfo(int id, double x, double y, double angle) {
    }

    public static List<Integer> findSequence(LaserInfo laser, List<PrismInfo> prisms) {
        var result = new ArrayList<Integer>();
        var source = laser;

        for (var target = nextPrism(source, prisms); target.isPresent(); target = nextPrism(source, prisms)) {
            var prism = target.get();
            result.add(prism.id());
            source = new LaserInfo(prism.x(), prism.y(), source.angle() + prism.angle());
        }
        return result;
    }

    static double distance(LaserInfo laser, PrismInfo prism) {
        return hypot(prism.x() - laser.x(), prism.y() - laser.y());
    }

    static Predicate<PrismInfo> isOnPath(LaserInfo laser) {
        var rad = toRadians(laser.angle());
        var cosA = cos(rad);
        var sinA = sin(rad);

        return prism -> {
            var dx = prism.x() - laser.x();
            var dy = prism.y() - laser.y();
            var dist = hypot(dx, dy);

            return dist > DELTA
                    && abs(cosA * dist - dx) < DELTA
                    && abs(sinA * dist - dy) < DELTA;
        };
    }

    static Optional<PrismInfo> nextPrism(LaserInfo laser, List<PrismInfo> prisms) {
        return prisms.stream()
                .filter(isOnPath(laser))
                .min(Comparator.comparingDouble(prism -> distance(laser, prism)));
    }
}
