#include <string>

namespace log_line {
    std::string message(const std::string& log_message) {
        auto found = log_message.find(':');
        return log_message.substr(found + 2);
    }

    std::string log_level(const std::string& log_message) {
        auto found = log_message.find(':');
        return log_message.substr(1, found - 2);
    }

    std::string reformat(const std::string& log_message) {
        return message(log_message) + " (" + log_level(log_message) + ")";
    }
}
