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
        self.strength = Character.ability()
        self.dexterity = Character.ability()
        self.constitution = Character.ability()
        self.intelligence = Character.ability()
        self.wisdom = Character.ability()
        self.charisma = Character.ability()
        self.hitpoints = 10 + Character.modifier(self.constitution)

    @staticmethod
    def ability() -> int:
        """Roll 4d6 and return the sum of the highest 3 dice."""
        rolls = sorted(random.randint(1, DICE_SIDES) for _ in range(DICE_COUNT_ROLLED))
        return sum(rolls[1:])

    @staticmethod
    def modifier(value: int) -> int:
        """Calculate the ability modifier for a given score."""
        return (value - ABILITY_SCORE_SUBTRACT) // ABILITY_MODIFIER_DIVISOR