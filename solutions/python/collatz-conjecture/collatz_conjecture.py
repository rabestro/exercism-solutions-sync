from functools import lru_cache


@lru_cache(maxsize=None)
def steps(number):
    if number < 1:
        raise ValueError("Only positive integers are allowed")
    if number == 1:
        return 0
    next_number = 3 * number + 1 if number % 2 else number // 2
    return 1 + steps(next_number)
