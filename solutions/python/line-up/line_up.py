"""Module to format customer line-up messages with proper ordinal suffixes."""


def line_up(name: str, number: int) -> str:
    return (f"{name}, you are the {number}{ordinal_suffix(number)} customer we serve today. Thank "
            f"you!")


def ordinal_suffix(number: int) -> str:
    return {
        1: "st",
        2: "nd",
        3: "rd",
    }.get(not 11 <= number % 100 <= 13 and number % 10, "th")
