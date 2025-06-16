#include <algorithm>
#include "reverse_string.h"

namespace reverse_string {
    string reverse_string(string phrase) {
        reverse(phrase.begin(), phrase.end());
        return phrase;
    }
}  // namespace reverse_string
