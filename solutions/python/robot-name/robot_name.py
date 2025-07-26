import itertools
import string

letters = string.ascii_uppercase
numbers = range(1000)
robot_name_generator = (
    f"{c1}{c2}{n:03}" for c1, c2, n in
    itertools.product(letters, letters, numbers)
)

class Robot:
    """
    Represents a robot with a unique name.

    The Robot class generates a unique name for each instance by using a
    provided name generator. Robots can reset their name, effectively assigning
    them a new unique identifier.

    Attributes:
        name (str): The unique name assigned to the robot.
    """
    def __init__(self):
        self.name = next(robot_name_generator)

    def reset(self) -> None:
        """Assigns a new name to the robot."""
        self.name = next(robot_name_generator)
