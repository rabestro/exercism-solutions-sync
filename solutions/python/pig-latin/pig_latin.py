import re

_WORD_PATTERN = re.compile(r"""
    (?P<consonants>                     # Capture group for possible consonants at the start of a word
        (?!xr|yt)                       # Look ahead negative. Words starting with "xr" or "yt" are treated as vowels.
        y?(qu|[bcdfghjklmnpqrstvwxz])*  # Allows optional 'y' at start, then 'qu' or any consonant.
    )?                                  # The entire consonant group is optional (for words starting with vowels).
    (?P<base>\w+)                       # Capture group for the base of the word (the rest of the letters).
    """, re.VERBOSE)

_PIG_LATIN_FORMAT = r"\g<base>\g<consonants>ay"


def translate(text):
    return _WORD_PATTERN.sub(_PIG_LATIN_FORMAT, text)
