#include "hexadecimal.h"

namespace {
    // Use a constexpr lambda for maximum locality and potential compile-time evaluation.
    constexpr auto hex_char_to_int = [](char c) -> std::optional<int> {
        if (c >= '0' && c <= '9') return c - '0';
        if (c >= 'a' && c <= 'f') return c - 'a' + 10;
        return std::nullopt;
    };
}

namespace hexadecimal {
    [[nodiscard]] int convert(std::string_view hexadecimal_number) noexcept {
        int decimal_number{0};
        for (const char digit_char: hexadecimal_number) {
            if (const auto value = hex_char_to_int(digit_char)) {
                decimal_number *= 16;
                decimal_number += *value;
            } else {
                return 0;
            }
        }
        return decimal_number;
    }
} // namespace hexadecimal
