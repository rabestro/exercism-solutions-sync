import functools
from string import ascii_uppercase, ascii_lowercase


@functools.cache
def _get_translation_map(key: int) -> dict[int, int]:
    """Create and cache the translation map for a given key."""

    key = key % 26
    original_chars = ascii_lowercase + ascii_uppercase
    shifted_lower = ascii_lowercase[key:] + ascii_lowercase[:key]
    shifted_upper = ascii_uppercase[key:] + ascii_uppercase[:key]
    shifted_chars = shifted_lower + shifted_upper
    return str.maketrans(original_chars, shifted_chars)


def rotate(text: str, key: int) -> str:
    """Rotate letters in text by the given key."""

    return text.translate(_get_translation_map(key))
