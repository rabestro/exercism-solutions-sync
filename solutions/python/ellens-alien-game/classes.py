"""Solution to Ellen's Alien Game exercise."""
from dataclasses import dataclass
from typing import ClassVar


@dataclass
class Alien:
    x_coordinate: int
    y_coordinate: int
    health: int = 3

    total_aliens_created: 'ClassVar[int]' = 0

    def __post_init__(self):
        Alien.total_aliens_created += 1

    def hit(self):
        self.health -= 1

    def is_alive(self):
        return self.health > 0

    def teleport(self, new_x_coordinate, new_y_coordinate):
        self.x_coordinate = new_x_coordinate
        self.y_coordinate = new_y_coordinate

    def collision_detection(self, other):
        pass


def new_aliens_collection(coordinates):
    return [Alien(x, y) for x, y in coordinates]
