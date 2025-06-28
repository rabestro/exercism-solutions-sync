COLOR_MAP = {
    'black': 0, 'brown': 1, 'red': 2, 'orange': 3, 'yellow': 4,
    'green': 5, 'blue': 6, 'violet': 7, 'grey': 8, 'white': 9
}

TOLERANCE_MAP = {
    'gold': 5, 'silver': 10, 'brown': 1, 'red': 2,
    'green': 0.5, 'blue': 0.25, 'violet': 0.1, 'grey': 0.05
}

SUFFIXES = (
    (1_000_000_000, 'gigaohms'),
    (1_000_000, 'megaohms'),
    (1_000, 'kiloohms'),
)


def label(colors: list[str]) -> str:
    significant_digits = COLOR_MAP[colors[0]] * 10 + COLOR_MAP[colors[1]]
    if len(colors) == 5:
        significant_digits *= 10
        significant_digits += COLOR_MAP[colors[2]]
    multiplier = 10 ** COLOR_MAP[colors[-2]]
    ohms = significant_digits * multiplier

    for divisor, suffix in SUFFIXES:
        if ohms >= divisor:
            value = ohms / divisor if ohms % divisor else ohms // divisor
            return f'{value} {suffix}'

    return f'{ohms} ohms'


def resistor_label(colors: list[str]) -> str:
    if len(colors) == 1:
        return f'{COLOR_MAP[colors[0]]} ohms'
    tolerance = TOLERANCE_MAP[colors[-1]]
    return f'{label(colors)} ±{tolerance}%'
