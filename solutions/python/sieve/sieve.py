import math
from itertools import compress


def primes(limit: int) -> list[int]:
    if limit < 2:
        return []

    is_prime = [True] * (limit + 1)
    is_prime[0] = is_prime[1] = False

    for number in range(2, math.isqrt(limit) + 1):
        if is_prime[number]:
            is_prime[number * number: limit + 1: number] = [False] * len(is_prime[number * number: limit + 1: number])

    return list(compress(range(limit + 1), is_prime))