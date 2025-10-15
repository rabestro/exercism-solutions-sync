#include "bob.h"

#include <regex>

namespace bob {
    auto no_content_pattern = std::regex("^\\s*$");
    auto yelling_question_pattern = std::regex("^[^a-z]*[A-Z][^a-z]*\\?$");
    auto question_pattern = std::regex(R"([\s\S]*\?\s*)");
    auto yelling_pattern = std::regex("[^a-z]*[A-Z][^a-z]*");

    std::string hey(std::string phrase) {
        if (std::regex_match(phrase, no_content_pattern)) {
            return "Fine. Be that way!";
        }
        if (std::regex_match(phrase, yelling_question_pattern)) {
            return "Calm down, I know what I'm doing!";
        }
        if (std::regex_match(phrase, question_pattern)) {
            return "Sure.";
        }
        if (std::regex_match(phrase, yelling_pattern)) {
            return "Whoa, chill out!";
        }
        return "Whatever.";
    }
} // namespace bob
