# Score categories.
# Change the values as you see fit.
from itertools import count

YACHT = 50
ONES = 1
TWOS = 2
THREES = 3
FOURS = 4
FIVES = 5
SIXES = 6
FULL_HOUSE = 7
FOUR_OF_A_KIND = 8
LITTLE_STRAIGHT = 9
BIG_STRAIGHT = 10
CHOICE = 11


def score(dice: list[int], category):
    match category:
        case 50:
            return 50 if len(set(dice)) == 1 else 0
        case 11:
            return sum(dice)
        case 7:
            return sum(dice) if 1 < dice.count(dice[0]) < 4 and len(set(dice)) == 2 else 0
        case 8:
            if dice.count(dice[0]) > 3:
                return 4 * dice[0]
            if dice.count(dice[1]) == 4:
                return 4 * dice[1]
            else:
                return 0
        case 9:
            return 30 if sorted(dice) == [1, 2, 3, 4, 5] else 0
        case 10:
            return 30 if sorted(dice) == [2, 3, 4, 5, 6] else 0
        case _:
            return sum(x for x in dice if x == category)
