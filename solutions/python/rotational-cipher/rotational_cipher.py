from functools import cache
import string

_LOWER = string.ascii_lowercase
_UPPER = string.ascii_uppercase
_ALPHABET = _LOWER + _UPPER


@cache
def _get_translation_map(key: int) -> dict[int, int]:
    """
    Creates and caches a translation map for a given rotation key.
    The @cache decorator saves the result for each key, so this
    expensive work is only ever done once per key.
    """
    rotated_alphabet = _LOWER[key:] + _LOWER[:key] + _UPPER[key:] + _UPPER[:key]
    return str.maketrans(_ALPHABET, rotated_alphabet)


def rotate(text: str, key: int) -> str:
    """Rotate letters in a text by a given key, preserving a case."""
    if key == 0 or key == 26:
        return text

    translation_map = _get_translation_map(key)
    return text.translate(translation_map)
