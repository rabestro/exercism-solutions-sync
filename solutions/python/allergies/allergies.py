from enum import IntFlag
from functools import cached_property


class Allergen(IntFlag):
    EGGS = 1
    PEANUTS = 2
    SHELLFISH = 4
    STRAWBERRIES = 8
    TOMATOES = 16
    CHOCOLATE = 32
    POLLEN = 64
    CATS = 128


class Allergies:
    def __init__(self, score: int):
        self._score = score

    def allergic_to(self, item: str) -> bool:
        """
        Checks if the score includes a specific allergen.
        Using .upper() handles case-insensitivity nicely.
        """
        try:
            flag = Allergen[item.upper()]
            return bool(self._score & flag)
        except KeyError:
            return False

    @cached_property
    def lst(self) -> list[str]:
        """
        Returns a sorted list of allergen names.
        Using cached_property since the score is immutable.
        """
        return [
            allergen.name.lower()
            for allergen in Allergen
            if self._score & allergen
        ]
