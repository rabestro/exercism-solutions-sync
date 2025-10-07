#pragma once
#include <string>
#include <unordered_set>

namespace allergies {
    class AllergyTest {
    public:
        explicit AllergyTest(unsigned int test) : value(test) {
        }

        unsigned int value;

        bool is_allergic_to(std::string_view item) {
            if (item == "eggs") {
                return value & 1;
            }
            if (item == "peanuts") {
                return value & 2;
            }
            if (item == "shellfish") {
                return value & 4;
            }
            if (item == "strawberries") {
                return value & 8;
            }
            if (item == "tomatoes") {
                return value & 16;
            }
            if (item == "chocolate") {
                return value & 32;
            }
            if (item == "pollen") {
                return value & 64;
            }
            if (item == "cats") {
                return value & 128;
            }
            return false;
        }

        std::unordered_set<std::string> get_allergies() {
            std::unordered_set<std::string> allergies;
            auto items = {
                "eggs", "peanuts", "shellfish", "strawberries",
                "tomatoes", "chocolate", "pollen", "cats"
            };

            for (auto item: items) {
                if (is_allergic_to(item)) {
                    allergies.insert(item);
                }
            }
            return allergies;
        };
    };

    AllergyTest allergy_test(unsigned int test);
}; // namespace allergies
