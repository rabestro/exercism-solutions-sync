#include "darts.h"

namespace darts {
    constexpr auto outer_radius_sq = 10.0 * 10.0;
    constexpr auto middle_radius_sq = 5.0 * 5.0;
    constexpr auto inner_radius_sq = 1.0 * 1.0;

    int score(double x, double y) noexcept {
        const auto distance_sq = x * x + y * y;
        if (distance_sq > outer_radius_sq) {
            return 0;
        }
        if (distance_sq > middle_radius_sq) {
            return 1;
        }
        if (distance_sq > inner_radius_sq) {
            return 5;
        }
        return 10;
    }
} // namespace darts
