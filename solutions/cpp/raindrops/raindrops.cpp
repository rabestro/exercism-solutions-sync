#include "raindrops.h"
#include <string>

namespace raindrops {
    std::string convert(const int number) {
        std::string sound;
        if (number % 3 == 0) sound += "Pling";
        if (number % 5 == 0) sound += "Plang";
        if (number % 7 == 0) sound += "Plong";
        if (sound.empty()) sound = std::to_string(number);
        return sound;
    }
}  // namespace raindrops
