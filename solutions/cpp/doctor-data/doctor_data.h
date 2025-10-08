#pragma once
#include <string>
#include <utility>

namespace star_map {
    enum System {
        Sol, BetaHydri, EpsilonEridani, AlphaCentauri, DeltaEridani, Omicron2Eridani
    };
}

namespace heaven {
    class Vessel {
    public:
        int generation;
        star_map::System current_system;
        int busters{0};
        std::string name;

        Vessel(std::string name, int generation) {
            this->name = std::move(name);
            this->generation = generation;
            this->current_system = star_map::System::Sol;
        }

        Vessel(std::string name, int generation, star_map::System current_system) {
            this->name = std::move(name);
            this->generation = generation;
            this->current_system = current_system;
        }

        Vessel replicate(std::string name) {
            return {name, generation + 1, current_system};
        }

        void make_buster() {
            busters++;
        }

        bool shoot_buster() {
            if (busters) {
                busters--;
                return true;
            }
            return false;
        }
    };

    inline bool in_the_same_system(const Vessel& a, const Vessel& b) {
        return a.current_system == b.current_system;
    }
    std::string get_older_bob(const Vessel& a, const Vessel& b);

}
