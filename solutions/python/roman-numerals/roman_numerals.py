NUMERALS = [
    (1000, "M"), (900, "CM"), (500, "D"), (400, "CD"),
    (100, "C"), (90, "XC"), (50, "L"), (40, "XL"),
    (10, "X"), (9, "IX"), (5, "V"), (4, "IV"),
    (1, "I")
]


def roman(number: int) -> str:
    """Convert an integer to a Roman numeral using divmod."""
    if not 0 < number < 4000:
        raise ValueError("Input must be an integer between 1 and 3999")

    parts = []
    for value, numeral in NUMERALS:
        count, number = divmod(number, value)
        parts.append(numeral * count)

    return "".join(parts)
