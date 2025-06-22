from math import sqrt


def classify(number: int) -> str:
    """
    Classifies a given positive integer as "perfect", "deficient", or "abundant"
    based on the sum of its proper divisors excluding the number itself. A number
    is classified as:

    - Perfect: if the sum of its divisors equals the number.
    - Deficient: if the sum of its divisors is less than the number.
    - Abundant: if the sum of its divisors is greater than the number.

    :param number: The positive integer to be classified.
    :type number: int
    :return: A string indicating the classification of the number ("perfect",
        "deficient", or "abundant").
    :rtype: str
    :raises ValueError: If the input number is not a positive integer.
    """
    if number <= 0:
        raise ValueError("Classification is only possible for positive integers.")

    aliquot_sum = (number > 1) + sum(
        sum({i, number // i}) for i in range(2, int(sqrt(number)) + 1) if not number % i
    )
    return "perfect" if aliquot_sum == number else "deficient" if aliquot_sum < number else "abundant"
