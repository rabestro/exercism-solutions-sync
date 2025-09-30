#include "trinary.h"

namespace trinary {
    int to_decimal(std::string_view trinary_number) noexcept {
        int decimal_number{0};
        for (const auto digit: trinary_number ) {
            if (digit < '0' || digit > '2') return 0;
            decimal_number *= 3;
            decimal_number += digit - '0';
        }
        return decimal_number;
    }
} // namespace trinary
