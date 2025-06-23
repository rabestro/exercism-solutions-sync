from enum import Enum
from more_itertools import windowed

class ListRelation(Enum):
    """Categorizes the relationship between two lists."""
    EQUAL = 1
    SUBLIST = 2
    SUPERLIST = 3
    UNEQUAL = 4

SUBLIST = ListRelation.SUBLIST
SUPERLIST = ListRelation.SUPERLIST
EQUAL = ListRelation.EQUAL
UNEQUAL = ListRelation.UNEQUAL


def is_contained(inner_list, outer_list):
    """Checks for containment using a memory-efficient sliding window."""
    if not inner_list:
        return True

    inner_tuple = tuple(inner_list)

    for window in windowed(outer_list, len(inner_list)):
        if window == inner_tuple:
            return True
    return False

def sublist(list_one: list, list_two: list) -> ListRelation:
    """
    Determines if list_one is a sublist, superlist, or equal to list_two.
    """
    if list_one == list_two:
        return ListRelation.EQUAL
    if not list_one:
        return ListRelation.SUBLIST
    if not list_two:
        return ListRelation.SUPERLIST

    if is_contained(list_one, list_two):
        return ListRelation.SUBLIST
    if is_contained(list_two, list_one):
        return ListRelation.SUPERLIST

    return ListRelation.UNEQUAL
