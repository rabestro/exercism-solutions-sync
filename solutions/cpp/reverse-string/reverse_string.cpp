#include <algorithm>
#include "reverse_string.h"

namespace reverse_string {
    std::string reverse_string(std::string phrase) {
        reverse(phrase.begin(), phrase.end());
        return phrase;
    }
}
