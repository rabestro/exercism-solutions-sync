import math


def aliquot_sum(number: int) -> int:
    """
    Calculates the aliquot sum of a number using a more Pythonic, set-based approach.
    """
    limit = int(math.sqrt(number))
    factors = {1}
    for i in range(2, limit + 1):
        if number % i == 0:
            factors.update({i, number // i})

    return sum(factors)


def classify(number):
    """ A perfect number equals the sum of its positive divisors.

    :param number: int a positive integer
    :return: str the classification of the input integer
    """

    aliquot = aliquot_sum(number)
    return "perfect" if aliquot == number else "deficient" if aliquot < number else "abundant"

