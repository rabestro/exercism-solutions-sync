#pragma once
#include <string_view>

namespace hexadecimal {
    [[nodiscard]] int convert(std::string_view trinary_number) noexcept;
} // namespace hexadecimal
