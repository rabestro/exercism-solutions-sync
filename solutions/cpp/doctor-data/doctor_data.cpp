#include "doctor_data.h"

std::string heaven::get_older_bob(const Vessel& a, const Vessel& b) {
    return a.generation <= b.generation ? a.name : b.name;
}
