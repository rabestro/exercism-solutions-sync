namespace targets {
    class Alien {
    public:
        Alien(int x, int y) : x_coordinate{x}, y_coordinate{y} {
        };
        int x_coordinate;
        int y_coordinate;

        [[nodiscard]] int get_health() const {
            return health;
        }

        [[nodiscard]] bool is_alive() const {
            return health > 0;
        }

        bool hit() {
            health -= is_alive();
            return true;
        }

        bool teleport(int x_new, int y_new) {
            x_coordinate = x_new;
            y_coordinate = y_new;
            return true;
        }

        [[nodiscard]] bool collision_detection(Alien other) const {
            return x_coordinate == other.x_coordinate && y_coordinate == other.y_coordinate;
        }

    private:
        int health{3};
    };
} // namespace targets
