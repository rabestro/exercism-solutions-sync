from typing import Sequence

COLOR_MAP = {
    'black': 0, 'brown': 1, 'red': 2, 'orange': 3, 'yellow': 4,
    'green': 5, 'blue': 6, 'violet': 7, 'grey': 8, 'white': 9
}


def value(colors: Sequence[str]) -> int:
    """
    Calculates the two-band resistor value from a list of color names.
    This version uses a more scalable string-joining technique.
    """
    return int("".join(str(COLOR_MAP[c]) for c in colors[:2]))
