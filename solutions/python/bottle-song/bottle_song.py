NUMBERS = ("no", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")


def hanging(bottles):
    suffix = "" if bottles == 1 else "s"
    return f"{NUMBERS[bottles]} green bottle{suffix} hanging on the wall"


def verse(bottles):
    current_hanging = hanging(bottles)
    next_hanging = hanging(bottles - 1).lower()
    return (
        f"{current_hanging},",
        f"{current_hanging},",
        f"And if one green bottle should accidentally fall,",
        f"There'll be {next_hanging}.",
        ""
    )


def recite(start: int, take=1) -> list[str]:
    """Recite verses from a countdown song, starting from a given number for a certain count.

    Args:
        start: The starting verse number to recite from.
        take: The number of verses to recite, taken in reverse order. Defaults to 1.

    Returns:
        A list of strings, where each string contains a line of the recited lyrics.
    """
    return list(line for i in range(start, start - take, -1) for line in verse(i))[:-1]
