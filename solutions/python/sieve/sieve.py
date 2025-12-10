import math
from itertools import compress


def primes(limit: int) -> list[int]:
    """Calculate prime numbers up to a specified limit using the Sieve of Eratosthenes.

    This function employs an optimized version of the Sieve, using a bytearray
    for memory efficiency and slice assignment for speed (pushing loops to C-level).

    Args:
        limit: The inclusive upper bound for the range of numbers to check.
               Must be an integer >= 0.

    Returns:
        A list of integers representing all prime numbers less than or equal to `limit`.

    Examples:
        >>> primes(10)
        [2, 3, 5, 7]
        >>> primes(1)
        []

    Time Complexity: O(N log log N)
    Space Complexity: O(N) (specifically N bytes)
    """
    if limit < 2:
        return []

    is_prime = bytearray([1]) * (limit + 1)
    is_prime[0] = is_prime[1] = 0
    zeros = memoryview(b'\x00' * (limit + 1))

    for number in range(2, math.isqrt(limit) + 1):
        if is_prime[number]:
            start = number * number
            num_composites = (limit - start) // number + 1
            is_prime[start::number] = zeros[:num_composites]

    return list(compress(range(limit + 1), is_prime))