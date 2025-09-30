#include "hexadecimal.h"

namespace hexadecimal {
    bool is_valid_hexadecimal_symbol(char symbol) {
        return (symbol >= '0' && symbol <= '9') || (symbol >= 'a' && symbol <= 'f');
    }

    int convert_digit(char symbol) {
        if (symbol >= '0' && symbol <= '9') return symbol - '0';
        return symbol - 'a' + 10;
    }

    int convert(std::string_view hexadecimal_number) noexcept {
        int decimal_number{0};
        for (const auto digit: hexadecimal_number) {
            if (!is_valid_hexadecimal_symbol(digit)) return 0;
            decimal_number *= 16;
            decimal_number += convert_digit(digit);
        }
        return decimal_number;
    }
} // namespace hexadecimal
