import re
from itertools import groupby


def decode(encoded_text: str) -> str:
    """Decodes a run-length encoded string using a regex."""
    return "".join(
        int(count or 1) * char
        for count, char in re.findall(r"(\d*)(\D)", encoded_text)
    )


def encode(plain_text: str) -> str:
    """
    Encodes a string using run-length encoding.
    For example, 'AAAABBBCCDAA' becomes '4A3B2C1D2A'.
    """
    return "".join(
        char if (count := sum(1 for _ in group)) == 1 else f"{count}{char}"
        for char, group in groupby(plain_text)
    )
