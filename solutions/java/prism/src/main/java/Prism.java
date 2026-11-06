import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.lang.Math.*;

public class Prism {

    private static final double DELTA = 0.01;

    public record LaserInfo(double x, double y, double angle) {
    }

    public record PrismInfo(int id, double x, double y, double angle) {
    }

    public static List<Integer> findSequence(LaserInfo laser, List<PrismInfo> prisms) {
        record Step(int prismId, LaserInfo nextLaser) {
        }

        UnaryOperator<Optional<Step>> nextStep = current -> current
                .map(Step::nextLaser)
                .flatMap(l -> nextPrism(l, prisms)
                        .map(p -> new Step(p.id(), new LaserInfo(p.x(), p.y(), l.angle() + p.angle()))));

        var firstStep = nextPrism(laser, prisms)
                .map(p -> new Step(p.id(), new LaserInfo(p.x(), p.y(), laser.angle() + p.angle())));

        return Stream.iterate(firstStep, Optional::isPresent, nextStep)
                .map(Optional::orElseThrow)
                .map(Step::prismId)
                .toList();
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
