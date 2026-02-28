"""Module to format customer line-up messages with proper ordinal suffixes."""


def line_up(name: str, number: int) -> str:
    return f"{name}, you are the {number}{ordinal_suffix(number)} customer we serve today. Thank you!"


def ordinal_suffix(number: int) -> str:
    if 11 <= number % 100 <= 13:
        return "th"
    match number % 10:
        case 1:
            return "st"
        case 2:
            return "nd"
        case 3:
            return "rd"
        case _:
            return "th"
