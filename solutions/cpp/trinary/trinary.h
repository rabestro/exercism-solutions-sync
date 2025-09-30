#pragma once
#include <string_view>

namespace trinary {
    [[nodiscard]] int to_decimal(std::string_view trinary_number) noexcept;
} // namespace trinary
