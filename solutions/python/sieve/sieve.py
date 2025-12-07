import math
from itertools import compress


def primes(limit: int) -> list[int]:
    if limit < 2:
        return []

    is_prime = bytearray([1]) * (limit + 1)
    is_prime[0] = is_prime[1] = 0

    for number in range(2, math.isqrt(limit) + 1):
        if is_prime[number]:
            start = number * number
            num_composites = (limit - start) // number + 1
            is_prime[start : limit + 1 : number] = b'\x00' * num_composites

    return list(compress(range(limit + 1), is_prime))