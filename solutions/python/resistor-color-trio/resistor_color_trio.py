from typing import Sequence

COLOR_MAP = {
    'black': 0, 'brown': 1, 'red': 2, 'orange': 3, 'yellow': 4,
    'green': 5, 'blue': 6, 'violet': 7, 'grey': 8, 'white': 9
}

SUFFIXES = (
    (1_000_000_000, 'gigaohms'),
    (1_000_000, 'megaohms'),
    (1_000, 'kiloohms'),
)

def label(colors: Sequence[str]) -> str:
    significant_digits = COLOR_MAP[colors[0]] * 10 + COLOR_MAP[colors[1]]
    multiplier = 10 ** COLOR_MAP[colors[2]]
    ohms = significant_digits * multiplier

    for divisor, suffix in SUFFIXES:
        if ohms >= divisor:
            return f'{ohms // divisor} {suffix}'

    return f'{ohms} ohms'
