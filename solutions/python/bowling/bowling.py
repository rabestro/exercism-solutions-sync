class BowlingGame:
    """A class to manage and score a game of bowling.

    This class keeps track of bowling rolls and calculates scores according to standard
    bowling rules, including handling strikes, spares, and the special tenth frame.

    Attributes:
        rolls (list): List of pins knocked down in each roll.
        current_frame (int): The current frame number (1-10).
        pins_on_lane (int): Number of pins currently on the lane.
        is_game_over (bool): Whether the game has ended.
        is_second_roll (bool): Whether this is the second roll in a frame.
        bonus_rolls (int): Number of bonus rolls remaining.
    """

    def __init__(self):
        self.rolls = []
        self.current_frame = 1
        self.pins_on_lane = 10
        self.is_game_over = False
        self.is_second_roll = False
        self.bonus_rolls = 0

    def roll(self, pins: int) -> None:
        """Updates the game state for a single roll of bowling pins.

        This method handles the logic for each roll, including updating frame counts,
        managing pins, and determining if bonus rolls are needed.

        Args:
            pins: The number of pins knocked down in this roll.

        Raises:
            Exception: If the game is already over.
            ValueError: If the number of pins is negative.
            ValueError: If the number of pins exceeds pins on the lane.
        """
        if self.is_game_over:
            raise Exception("Game is over")
        if pins < 0:
            raise ValueError("Negative roll is invalid")
        if pins > self.pins_on_lane:
            raise ValueError("Pin count exceeds pins on the lane")

        self.pins_on_lane -= pins
        self.rolls.append(pins)

        match (self.current_frame, self.pins_on_lane, self.is_second_roll):
            case _, 0, _ if self.bonus_rolls == 2:
                self.bonus_rolls = 1
                self.pins_on_lane = 10
            case _ if self.bonus_rolls:
                self.bonus_rolls -= 1
                self.is_game_over = not self.bonus_rolls
            case 10, 0, False:
                self.bonus_rolls = 2
                self.pins_on_lane = 10
            case 10, 0, True:
                self.bonus_rolls = 1
                self.pins_on_lane = 10
            case 10, _, True:
                self.is_game_over = True
            case _, 0, _:
                self.current_frame += 1
                self.pins_on_lane = 10
                self.is_second_roll = False
            case _, _, False:
                self.is_second_roll = True
            case _:
                self.current_frame += 1
                self.pins_on_lane = 10
                self.is_second_roll = False

    def score(self) -> int:
        """Calculates and returns the total score for the bowling game.

        Calculates the final score according to standard bowling rules, including
        bonus points for strikes and spares.

        Returns:
            The total score for the completed game.

        Raises:
            Exception: If the game is not yet over.
        """
        if not self.is_game_over:
            raise Exception("Game is not over")
        index = 0
        score = 0
        for frame in range(10):
            # self.rolls[index] + self.rolls[index + 1]
            two_roll_score = self.rolls[index] + self.rolls[index + 1]
            score += two_roll_score
            if self.rolls[index] == 10:
                score += self.rolls[index + 2]
                index += 1
                continue
            index += 2
            if two_roll_score == 10:
                score += self.rolls[index]

        return score
