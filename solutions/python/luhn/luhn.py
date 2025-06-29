class Luhn:
    """A class to validate numbers using the Luhn algorithm.

    The Luhn algorithm is a simple checksum formula used to validate a variety
    of identification numbers, such as credit card numbers.

    Attributes:
        raw_input (str): The original string provided upon initialization.
        processed_str (str): The input string with whitespace removed.
    """

    def __init__(self, card_num: str):
        """Initializes the Luhn object.

        Args:
            card_num: The number string to be validated.
        """
        self.raw_input = card_num
        self.processed_str = card_num.replace(' ', '')

    def valid(self) -> bool:
        """Validates the number using the Luhn algorithm.

        The validation checks if the string contains only digits (after removing
        spaces), has a length greater than one, and satisfies the Luhn
        checksum formula.

        Returns:
            True if the number is valid according to the Luhn formula,
            False otherwise.
        """
        if len(self.processed_str) < 2 or not self.processed_str.isdigit():
            return False

        digits = map(int, reversed(self.processed_str))
        doubled_digits = (d * (1 + i % 2) for i, d in enumerate(digits))
        processed_digits = (d - 9 if d > 9 else d for d in doubled_digits)

        luhn_sum = sum(processed_digits)

        return luhn_sum % 10 == 0
