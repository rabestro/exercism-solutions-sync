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


def format_resistance(ohms: int) -> str:
    for divisor, suffix in SUFFIXES:
        if ohms >= divisor:
            value = ohms / divisor if ohms % divisor else ohms // divisor
            return f'{value} {suffix}'
    return f'{ohms} ohms'


def resistor_label(colors: list[str]) -> str:
    """
    Calculate the full label for a resistor based on its color bands.

    This function interprets a list of color names to produce a formatted
    string that includes the resistance value with its SI prefix (e.g.,
    kiloohms) and its tolerance percentage.

    Args:
        colors: A list of 1, 4, or 5 color name strings.

    Returns:
        A formatted string like "10 kiloohms ±5%".
    """
    if len(colors) == 1:
        return f'{COLOR_MAP[colors[0]]} ohms'
    *value_colors, multiplier_color, tolerance_color = colors
    ohms = 0
    for color in value_colors:
        ohms *= 10
        ohms += COLOR_MAP[color]
    ohms *= 10 ** COLOR_MAP[multiplier_color]
    tolerance = TOLERANCE_MAP[tolerance_color]
    return f'{format_resistance(ohms)} ±{tolerance}%'
