#include "two_fer.h"

#include <string>

namespace two_fer {
    std::string two_fer(const std::string &name) {
        return "One for " + name + ", one for me.";
    }
} // namespace two_fer
