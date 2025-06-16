#include "difference_of_squares.h"

namespace difference_of_squares {
    int square_of_sum(int x) {
        const int sum = x * (x + 1) / 2;
        return sum * sum;
    }

    int sum_of_squares(int x) {
        return x * (x + 1) * (2 * x + 1) / 6;
    }

    int difference(int x) {
        return square_of_sum(x) - sum_of_squares(x);
    }
}  // namespace difference_of_squares
