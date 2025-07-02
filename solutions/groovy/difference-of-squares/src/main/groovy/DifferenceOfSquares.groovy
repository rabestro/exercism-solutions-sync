class DifferenceOfSquares {
    int num

    DifferenceOfSquares(num) {
        this.num = num
    }

    def squareOfSum() {
        def sum = num * (num + 1) / 2
        sum * sum
    }

    def sumOfSquares() {
        num * (num + 1) * (2 * num + 1) / 6
    }

    def difference() {
        squareOfSum() - sumOfSquares()
    }

}
