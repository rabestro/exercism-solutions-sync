import functools
import string

ALPHABET_LOWER = string.ascii_lowercase
ALPHABET_UPPER = string.ascii_uppercase


@functools.cache
def _get_translation_map(key: int) -> dict[int, int]:
    """
    Creates and caches the translation map for a given key.
    The result is memoized, so this heavy lifting is only done once per key.
    """
    key = key % 26

    original_chars = ALPHABET_LOWER + ALPHABET_UPPER

    shifted_lower = ALPHABET_LOWER[key:] + ALPHABET_LOWER[:key]
    shifted_upper = ALPHABET_UPPER[key:] + ALPHABET_UPPER[:key]
    shifted_chars = shifted_lower + shifted_upper

    return str.maketrans(original_chars, shifted_chars)


def rotate(text: str, key: int) -> str:
    """Rotates letters in text by the given key."""
    if key == 0 or key == 26:
        return text

    translation_map = _get_translation_map(key)
    return text.translate(translation_map)
