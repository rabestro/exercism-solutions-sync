#include "bob.h"

#include <regex>

namespace bob {
    auto EMPTY = std::regex("^\\s*$");
    auto YELLING_QUESTION_PATTERN = std::regex("^[^a-z]*[A-Z][^a-z]*\\?$");
    auto QUESTION = std::regex(R"([\s\S]*\?\s*)");
    auto EXCLAMATION = std::regex("[^a-z]*[A-Z][^a-z]*");

    std::string hey(std::string phrase) {
        if (std::regex_match(phrase, EMPTY)) {
            return "Fine. Be that way!";
        }
        if (std::regex_match(phrase, YELLING_QUESTION_PATTERN)) {
            return "Calm down, I know what I'm doing!";
        }
        if (std::regex_match(phrase, QUESTION)) {
            return "Sure.";
        }
        if (std::regex_match(phrase, EXCLAMATION)) {
            return "Whoa, chill out!";
        }
        return "Whatever.";
    }
} // namespace bob
