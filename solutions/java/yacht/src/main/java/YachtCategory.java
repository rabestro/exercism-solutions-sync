import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static java.util.Arrays.stream;

enum YachtCategory implements Predicate<int[]> {
    YACHT(_ -> 50, haveNDifferentElements(1)),
    ONES(any(1)),
    TWOS(any(2)),
    THREES(any(3)),
    FOURS(any(4)),
    FIVES(any(5)),
    SIXES(any(6)),
    FULL_HOUSE(dice -> stream(dice).sum(), haveNDifferentElements(2).and(hasTreeSame())),
    FOUR_OF_A_KIND(dice -> stream(dice).sorted().skip(1).limit(1).sum() * 4) {
        @Override
        public boolean test(int[] dice) {
            return stream(dice).distinct().count() < 3
                    && stream(dice).sorted().skip(1).limit(3).distinct().count() == 1;
        }
    },
    LITTLE_STRAIGHT(_ -> 30, YachtCategory::isLowStraight),
    BIG_STRAIGHT(_ -> 30, YachtCategory::bigStraight),
    CHOICE(dice -> stream(dice).sum());

    private final ToIntFunction<int[]> scoreFormula;
    private final Predicate<int[]> applyTo;

    YachtCategory(ToIntFunction<int[]> scoreFormula, Predicate<int[]> applyTo) {
        this.scoreFormula = scoreFormula;
        this.applyTo = applyTo;
    }

    YachtCategory(ToIntFunction<int[]> scoreFormula) {
        this(scoreFormula, _ -> true);
    }

    public int calculateScore(int[] dice) {
        return test(dice) ? scoreFormula.applyAsInt(dice) : 0;
    }

    @Override
    public boolean test(int[] dice) {
        return applyTo.test(dice);
    }

    static Predicate<int[]> haveNDifferentElements(int number) {
        return dice -> stream(dice).distinct().count() == number;
    }

    private static Predicate<int[]> hasTreeSame() {
        return dice -> stream(dice).sorted().skip(1).limit(3).distinct().count() == 2;
    }

    private static boolean isLowStraight(int[] dice) {
        return haveNDifferentElements(5).test(dice) && stream(dice).max().orElse(0) == 5;
    }

    private static boolean bigStraight(int[] dice) {
        return haveNDifferentElements(5).test(dice) && stream(dice).min().orElse(0) == 2;
    }

    private static ToIntFunction<int[]> any(int number) {
        return dice -> stream(dice).filter(i -> i == number).sum();
    }

}
