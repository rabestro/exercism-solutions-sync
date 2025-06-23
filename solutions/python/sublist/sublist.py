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

_SEP = '\x1e\x1f'

def sublist(list_one: list, list_two: list) -> ListRelation:
    if list_one == list_two:
        return ListRelation.EQUAL
    if not list_one:
        return ListRelation.SUBLIST
    if not list_two:
        return ListRelation.SUPERLIST

    str_one = _SEP + _SEP.join(map(repr, list_one)) + _SEP
    str_two = _SEP + _SEP.join(map(repr, list_two)) + _SEP
    if str_one in str_two:
        return ListRelation.SUBLIST
    if str_two in str_one:
        return ListRelation.SUPERLIST
    return ListRelation.UNEQUAL
