import math


def triplets_with_sum(n: int) -> list[list[int]]:
    """Find all Pythagorean triplets (a, b, c) whose sum equals n.

    A Pythagorean triplet is a set of three natural numbers (a, b, c) where:
    a² + b² = c² and a < b < c

    Args:
        n: The sum that the triplet should add up to.

    Returns:
        A list of lists, where each inner list contains three integers [a, b, c]
        representing a Pythagorean triplet that sums to n.
    """
    return [
        [(n - c - d) // 2, (n - c + d) // 2, c]
        for c in range(n // 2 - 1, int((math.sqrt(2) - 1) * n), -1)
        if (d := math.isqrt(x := c ** 2 - n ** 2 + 2 * n * c)) * d == x
    ]
