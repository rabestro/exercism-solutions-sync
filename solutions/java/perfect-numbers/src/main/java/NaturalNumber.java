import java.util.stream.IntStream;

import static java.util.stream.IntStream.rangeClosed;

record NaturalNumber(int number) {
    public NaturalNumber {
        if (number < 1) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
    }

    private int aliquotSum() {
        return number < 2 ? 0 : 1 +
                rangeClosed(2, (int) Math.sqrt(number))
                        .filter(i -> number % i == 0)
                        .flatMap(i -> IntStream.of(i, number / i))
                        .distinct()
                        .sum();
    }

    public Classification getClassification() {
        int comparison = Integer.compare(aliquotSum(), number());
        return switch (comparison) {
            case -1 -> Classification.DEFICIENT;
            case 1 -> Classification.ABUNDANT;
            default -> Classification.PERFECT;
        };
    }
}
