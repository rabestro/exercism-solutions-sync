_WORDS = {
    0: 'zero', 1: 'one', 2: 'two', 3: 'three', 4: 'four', 5: 'five',
    6: 'six', 7: 'seven', 8: 'eight', 9: 'nine', 10: 'ten',
    11: 'eleven', 12: 'twelve', 13: 'thirteen', 14: 'fourteen', 15: 'fifteen',
    16: 'sixteen', 17: 'seventeen', 18: 'eighteen', 19: 'nineteen',
    20: 'twenty', 30: 'thirty', 40: 'forty', 50: 'fifty',
    60: 'sixty', 70: 'seventy', 80: 'eighty', 90: 'ninety'
}

_MAGNITUDES = (
    (1_000_000_000, 'billion'),
    (1_000_000, 'million'),
    (1_000, 'thousand'),
    (100, 'hundred'),
)


def say(number: int) -> str:
    """Converts a number to its English word representation."""
    if not 0 <= number <= 999_999_999_999:
        raise ValueError("input out of range")
    return _number_to_words(number)


def _number_to_words(number: int) -> str:
    if number in _WORDS:
        return _WORDS[number]

    if number < 100:
        tens, remainder = divmod(number, 10)
        return f"{_WORDS[tens * 10]}-{_WORDS[remainder]}"

    for divisor, name in _MAGNITUDES:
        if number >= divisor:
            quotient, remainder = divmod(number, divisor)
            quotient_part = _number_to_words(quotient) + f" {name}"
            remainder_part = f" {_number_to_words(remainder)}" if remainder else ""
            return quotient_part + remainder_part

    raise AssertionError("Unhandled number case in _number_to_words")
