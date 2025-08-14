import re
from itertools import groupby


def decode(encoded_text: str) -> str:
    """
    Decodes a run-length encoded string.
    For example, '4A3B2CD2A' becomes 'AAAABBBCCDAA'.
    """
    count = 0
    plan_text = ""
    for char in encoded_text:
        if char.isdigit():
            count *= 10
            count += int(char)
        else:
            count += count == 0
            plan_text += char * count
            count = 0
    return plan_text


def encode(plain_text: str) -> str:
    """
    Encodes a string using run-length encoding.
    For example, 'AAAABBBCCDAA' becomes '4A3B2C1D2A'.
    """
    return "".join(
        char if (count := sum(1 for _ in group)) == 1 else f"{count}{char}"
        for char, group in groupby(plain_text)
    )
