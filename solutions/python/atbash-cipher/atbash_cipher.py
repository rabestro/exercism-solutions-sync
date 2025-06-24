import textwrap
from string import ascii_lowercase

_TRANS = str.maketrans(ascii_lowercase, ascii_lowercase[::-1])

def normalize(text:str):
    return "".join(c.lower() for c in text if c.isalnum())


def encode(plain_text: str)->str:
    text = normalize(plain_text).translate(_TRANS)
    return " ".join(textwrap.wrap(text, 5))


def decode(ciphered_text:str)->str:
    return ciphered_text.replace(' ', '').translate(_TRANS)
