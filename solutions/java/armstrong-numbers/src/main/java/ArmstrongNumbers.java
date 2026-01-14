class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        if (numberToCheck < 0) return false;
        if (numberToCheck == 0) return true;

        int sum = 0;
        int digitCount = (int) Math.log10(numberToCheck) + 1;

        for (int i = numberToCheck; i > 0; i /= 10) {
            int digit = i % 10;
            sum += pow(digit, digitCount);
            if (sum > numberToCheck) return false;
        }

        return sum == numberToCheck;
    }

    private int pow(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }
}