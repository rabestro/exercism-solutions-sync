"""
This exercise stub and the test suite contain several enumerated constants.

Enumerated constants can be done with a NAME assigned to an arbitrary,
but unique value. An integer is traditionally used because it’s memory
efficient.
It is a common practice to export both constants and functions that work with
those constants (ex. the constants in the os, subprocess and re modules).

You can learn more here: https://en.wikipedia.org/wiki/Enumerated_type
"""

# Possible sublist categories.
# Change the values as you see fit.
SUBLIST = 1
SUPERLIST = 2
EQUAL = 3
UNEQUAL = 4

_SEP = '\x1e\x1f'

def sublist(list_one: list, list_two: list) -> int:
    if list_one == list_two:
        return EQUAL
    if not list_one:
        return SUBLIST
    if not list_two:
        return SUPERLIST

    str_one = _SEP + _SEP.join(map(repr, list_one)) + _SEP
    str_two = _SEP + _SEP.join(map(repr, list_two)) + _SEP
    if str_one in str_two:
        return SUBLIST
    if str_two in str_one:
        return SUPERLIST
    return UNEQUAL
