import re
from itertools import groupby


def decode(encoded_text: str) -> str:
    """
    Decodes a run-length encoded string.
    For example, '4A3B2CD2A' becomes 'AAAABBBCCDAA'.
    """
    # Find either a number-and-character pair OR a single character
    return re.sub(r'(\d+)(\D)|(\D)',
                  lambda m: m.group(2) * int(m.group(1)) if m.group(1) else m.group(3),
                  encoded_text)


def encode(plain_text: str) -> str:
    """
    Encodes a string using run-length encoding.
    For example, 'AAAABBBCCDAA' becomes '4A3B2C1D2A'.
    """
    return "".join(
        char if (count := sum(1 for _ in group)) == 1 else f"{count}{char}"
        for char, group in groupby(plain_text)
    )
