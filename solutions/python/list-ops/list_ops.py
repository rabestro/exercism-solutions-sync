def append(list1: list, list2: list) -> list:
    return list1 + list2


def concat(lists: list[list]) -> list:
    result = []
    for l in lists:
        result += l
    return result


def filter(function, x: list) -> list:
    return [i for i in x if function(i)]


def length(list):
    return len(list)


def map(function, list):
    return [function(i) for i in list]


def foldl(function, list, initial):
    for i in list:
        initial = function(initial, i)
    return initial


def foldr(function, list, initial):
    return foldl(function, list[::-1], initial)


def reverse(list):
    return list[::-1]
