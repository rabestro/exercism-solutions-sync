import random

# Constants clearly define the rules of the game
ABILITY_SCORE_SUBTRACT = 10
ABILITY_MODIFIER_DIVISOR = 2
DICE_SIDES = 6
DICE_COUNT_ROLLED = 4


class Character:
    """
    Represents a D&D character with randomly generated ability scores.
    """

    def __init__(self):
        """Initializes a character with random stats."""
        self.strength = ability()
        self.dexterity = ability()
        self.constitution = ability()
        self.intelligence = ability()
        self.wisdom = ability()
        self.charisma = ability()
        self.hitpoints = 10 + modifier(self.constitution)


def ability() -> int:
    """Roll 4d6 and return the sum of the highest 3 dice."""
    rolls = sorted(random.randint(1, DICE_SIDES) for _ in range(DICE_COUNT_ROLLED))
    return sum(rolls[1:])


def modifier(value: int) -> int:
    """Calculate the ability modifier for a given score."""
    return (value - ABILITY_SCORE_SUBTRACT) // ABILITY_MODIFIER_DIVISOR
