from enum import Enum

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

    len_one, len_two = len(list_one), len(list_two)
    if len_one < len_two:
        for i in range(len_two - len_one + 1):
            if list_one == list_two[i:i + len_one]:
                return ListRelation.SUBLIST
    elif len_one > len_two:
        for i in range(len_one - len_two + 1):
            if list_two == list_one[i:i + len_two]:
                return ListRelation.SUPERLIST

    return ListRelation.UNEQUAL
