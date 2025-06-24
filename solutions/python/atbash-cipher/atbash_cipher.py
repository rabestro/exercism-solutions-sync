from string import ascii_lowercase
from textwrap import wrap

_TRANS = str.maketrans(ascii_lowercase, ascii_lowercase[::-1])


def cipher(text: str) -> str:
    return "".join(c for c in text.lower() if c.isalnum()).translate(_TRANS)


def encode(plain_text: str) -> str:
    return " ".join(wrap(cipher(plain_text), 5))


def decode(ciphered_text: str) -> str:
    return cipher(ciphered_text)
