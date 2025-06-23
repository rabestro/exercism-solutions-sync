from enum import Enum

class ListRelation(Enum):
    """Categorizes the relationship between two lists."""
    EQUAL = 1
    SUBLIST = 2
    SUPERLIST = 3
    UNEQUAL = 4

# Exposing constants for the test runner.
SUBLIST = ListRelation.SUBLIST
SUPERLIST = ListRelation.SUPERLIST
EQUAL = ListRelation.EQUAL
UNEQUAL = ListRelation.UNEQUAL


def is_contained(inner: list, outer: list) -> bool:
    return not (inner_len := len(inner)) or any(
        inner == outer[i:i + inner_len]
        for i in range(len(outer) - inner_len + 1)
    )

def sublist(list_one: list, list_two: list) -> ListRelation:
    """
    Determines if list_one is a sublist, superlist, or equal to list_two.
    """
    if list_one == list_two:
        return ListRelation.EQUAL

    len_one, len_two = len(list_one), len(list_two)
    if len_one < len_two and is_contained(list_one, list_two):
        return ListRelation.SUBLIST
    if len_one > len_two and is_contained(list_two, list_one):
        return ListRelation.SUPERLIST

    return ListRelation.UNEQUAL
