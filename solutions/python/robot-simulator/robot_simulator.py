NORTH = 1j
EAST = 1 + 0j
SOUTH = -1j
WEST = -1 + 0j


class Robot:
    def __init__(self, direction=NORTH, x_pos=0, y_pos=0):
        self.direction = direction
        self.position = complex(x_pos, y_pos)

    @property
    def coordinates(self) -> tuple[int, int]:
        return int(self.position.real), int(self.position.imag)

    def move(self, commands: str) -> None:
        for command in commands:
            match command:
                case "A":
                    self.position += self.direction
                case "R":
                    self.direction *= -1j
                case "L":
                    self.direction *= 1j
