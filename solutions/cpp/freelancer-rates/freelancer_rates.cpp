#include <cmath>

constexpr auto hours_per_day{8};
constexpr auto billable_days_per_month{22};

// daily_rate calculates the daily rate given an hourly rate
double daily_rate(double hourly_rate) {
    return hourly_rate * hours_per_day;
}

// apply_discount calculates the price after a discount
double apply_discount(double before_discount, double discount) {
    return before_discount * (1 - discount / 100);
}

// monthly_rate calculates the monthly rate, given an hourly rate and a discount
int monthly_rate(double hourly_rate, double discount) {
    const auto monthly = apply_discount(billable_days_per_month * daily_rate(hourly_rate), discount);
    return static_cast<int>(std::ceil(monthly));
}

// days_in_budget calculates the number of workdays given a budget, hourly rate, and discount
int days_in_budget(int budget, double hourly_rate, double discount) {
    const auto daily = apply_discount(daily_rate(hourly_rate), discount);
    return static_cast<int>(std::floor(budget / daily)); // Used std::floor for clarity
}