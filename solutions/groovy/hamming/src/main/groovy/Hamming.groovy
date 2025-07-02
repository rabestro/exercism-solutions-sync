class Hamming {

    int distance(String strand1, String strand2) {
        if (strand1.length() != strand2.length()) throw new ArithmeticException()
        if (strand1.isEmpty()) return 0;

        int difference = 0
        int i = strand2.length()
        while (i-- > 0) if (strand2[i] != strand1[i]) difference++

        difference
    }

}