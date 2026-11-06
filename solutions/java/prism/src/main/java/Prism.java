import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.Math.*;

public class Prism {

    private static final double DELTA = 0.01;

    public record LaserInfo(double x, double y, double angle) {
        LaserInfo deflect(PrismInfo p) {
            return new LaserInfo(p.x(), p.y(), angle + p.angle());
        }

        double distanceTo(PrismInfo p) {
            return hypot(p.x() - x, p.y() - y);
        }

        boolean hits(PrismInfo p) {
            var dist = distanceTo(p);
            var rad = toRadians(angle);
            return dist > DELTA
                    && abs(cos(rad) * dist - (p.x() - x)) < DELTA
                    && abs(sin(rad) * dist - (p.y() - y)) < DELTA;
        }
    }

    public record PrismInfo(int id, double x, double y, double angle) {
    }

    public static List<Integer> findSequence(LaserInfo laser, List<PrismInfo> prisms) {
        record Step(int id, LaserInfo laser) {}

        Function<LaserInfo, Optional<Step>> step = l -> prisms.stream()
                .filter(l::hits)
                .min(Comparator.comparingDouble(l::distanceTo))
                .map(p -> new Step(p.id(), l.deflect(p)));

        return Stream.iterate(step.apply(laser), Optional::isPresent, opt -> opt.map(Step::laser).flatMap(step))
                .map(opt -> opt.orElseThrow().id())
                .toList();
    }
}
