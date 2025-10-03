#pragma once
#include <string_view>
#include <ranges>

namespace luhn {
    [[nodiscard]] constexpr bool valid(std::string_view card_number) noexcept {
        int length{0};
        int sum{0};
        for (const unsigned char digit_char: card_number | std::views::reverse) {
            if (std::isspace(digit_char)) continue;
            if (!std::isdigit(digit_char)) return false;
            const int digit{(digit_char - '0') * (1 + length++ % 2)};
            sum += digit > 9 ? digit - 9 : digit;
        }
        return length > 1 && sum % 10 == 0;
    }
} // namespace luhn
