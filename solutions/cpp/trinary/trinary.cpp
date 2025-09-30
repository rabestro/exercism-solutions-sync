#include "trinary.h"

#include <string_view>

namespace trinary {
    int to_decimal(std::string_view trinary_number) {
        int decimal_number{0};
        int power_of_3{1};
        for (size_t i{trinary_number.length()}; i-- > 0; power_of_3 *= 3) {
            const int digit{trinary_number[i] - '0'};
            if (digit < 0 || digit > 2) return 0;
            decimal_number += digit * power_of_3;
        }
        return decimal_number;
    }
} // namespace trinary
