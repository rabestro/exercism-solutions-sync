"""Module to format customer line-up messages with proper ordinal suffixes."""


def line_up(name: str, number: int) -> str:
    return f"{name}, you are the {number}{ordinal_suffix(number)} customer we serve today. Thank you!"


def ordinal_suffix(number: int) -> str:
    if 11 <= number % 100 <= 13:
        return "th"
    return {1: "st", 2: "nd", 3: "rd"}.get(number % 10, "th")
