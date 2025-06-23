_ALPHABET = 'abcdefghijklmnopqrstuvwxyz'


def rotate(text: str, key: int) -> str:
    original = _ALPHABET + _ALPHABET.upper()
    shift = _ALPHABET[key:] + _ALPHABET[:key]
    target = shift + shift.upper()
    translation_map = str.maketrans(original, target)
    return text.translate(translation_map)
