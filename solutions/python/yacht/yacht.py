# Score categories.
# Change the values as you see fit.

ONES = 1
TWOS = 2
THREES = 3
FOURS = 4
FIVES = 5
SIXES = 6
FULL_HOUSE = 'FULL_HOUSE'
FOUR_OF_A_KIND = 'FOUR_OF_A_KIND'
LITTLE_STRAIGHT = 'LITTLE_STRAIGHT'
BIG_STRAIGHT = 'BIG_STRAIGHT'
CHOICE = 'CHOICE'
YACHT = 'YACHT'


def score(dice: list[int], category):
    match category:
        case 'YACHT':
            return 50 if len(set(dice)) == 1 else 0
        case 'CHOICE':
            return sum(dice)
        case 'FULL_HOUSE':
            return sum(dice) if 1 < dice.count(dice[0]) < 4 and len(set(dice)) == 2 else 0
        case 'FOUR_OF_A_KIND':
            if dice.count(dice[0]) > 3:
                return 4 * dice[0]
            if dice.count(dice[1]) == 4:
                return 4 * dice[1]
            else:
                return 0
        case 'LITTLE_STRAIGHT':
            return 30 if sorted(dice) == [1, 2, 3, 4, 5] else 0
        case 'BIG_STRAIGHT':
            return 30 if sorted(dice) == [2, 3, 4, 5, 6] else 0
        case _:
            return sum(x for x in dice if x == category)
