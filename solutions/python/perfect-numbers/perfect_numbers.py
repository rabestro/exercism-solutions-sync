from math import sqrt


def classify(number: int) -> str:
    """ A perfect number equals the sum of its positive divisors.

    :param number: int a positive integer
    :return: str the classification of the input integer
    """

    if number <= 0:
        raise ValueError("Classification is only possible for positive integers.")

    aliquot_sum = (number > 1) + sum(
        (sum({i, number // i}) for i in range(2, int(sqrt(number)) + 1) if not number % i))
    return "perfect" if aliquot_sum == number else "deficient" if aliquot_sum < number else "abundant"
