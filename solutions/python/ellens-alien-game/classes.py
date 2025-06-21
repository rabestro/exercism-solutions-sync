"""Solution to Ellen's Alien Game exercise."""


class Alien:
    """Create an Alien object with location x_coordinate and y_coordinate."""
    total_aliens_created = 0

    def __init__(self, x_coordinate, y_coordinate):
        self.x_coordinate = x_coordinate
        self.y_coordinate = y_coordinate
        self.health = 3
        Alien.total_aliens_created += 1

    def __repr__(self):
        # A great repr shows how to recreate the object
        return f"Alien(x_coordinate={self.x_coordinate}, y_coordinate={self.y_coordinate})"

    def __str__(self):
        # A friendly string for printing
        return f"An alien at ({self.x_coordinate}, {self.y_coordinate}) with {self.health} health."

    def hit(self):
        """Decrement Alien health by one point."""
        self.health -= 1

    def is_alive(self):
        """Return a boolean for if Alien is alive (if health is > 0)."""
        return self.health > 0

    def teleport(self, new_x_coordinate, new_y_coordinate):
        """Move Alien object to new coordinates."""
        self.x_coordinate = new_x_coordinate
        self.y_coordinate = new_y_coordinate

    def collision_detection(self, other):
        """Implementation TBD."""
        pass


def new_aliens_collection(coordinates):
    """Create a list of Alien objects at the specified coordinates."""

    return [Alien(x, y) for x, y in coordinates]
