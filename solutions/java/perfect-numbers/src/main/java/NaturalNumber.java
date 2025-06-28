import java.util.stream.IntStream;

import static java.util.stream.IntStream.rangeClosed;

record NaturalNumber(int number) {
    public NaturalNumber {
        if (number < 1) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
    }

    public Classification getClassification() {
        int aliquotSum = rangeClosed(1, number / 2)
                .filter(i -> number % i == 0)
                .sum();
        if (aliquotSum < number) {
            return Classification.DEFICIENT;
        }
        if (aliquotSum > number) {
            return Classification.ABUNDANT;
        }
        return Classification.PERFECT;
    }
}
