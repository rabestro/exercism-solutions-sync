"""A module for translating English text to Pig Latin.

This implementation uses a single, pre-compiled regular expression to handle
all translation rules efficiently in one pass using `re.sub`.
"""
import re

_WORD_PATTERN = re.compile(r"""
    \b                                  # Assert position at a word boundary.
    (?P<consonants>                     # Capture group for possible consonants at the start of a word.
        (?!xr|yt)                       # Negative lookahead: "xr" and "yt" are vowel sounds.
        y?(qu|[bcdfghjklmnpqrstvwxz])*  # Optional 'y', then 'qu' or any other consonant.
    )?                                  # The entire consonant group is optional (for vowel-led words).
    (?P<base>\w+)                       # Capture group for the rest of the word.
    \b                                  # Assert position at a word boundary.
    """, re.VERBOSE)

_PIG_LATIN_FORMAT = r"\g<base>\g<consonants>ay"


def translate(text):
    """Translate a given English text into Pig Latin.

    This function applies a set of Pig Latin rules to each word in the
    input string. The translation is performed using a single, efficient
    regular expression substitution.

    The rules handled by the regex are:

    1.  Words that start with a vowel sound (a, e, i, o, u, xr, yt)
        have "ay" appended.
    2.  Words that start with a consonant sound have the initial consonant
        cluster (including 'qu' and 'y' as a consonant) moved to the end
        of the word, followed by "ay".

    Args:
        text (str): The English text to be translated. Can contain one
            or more words.

    Returns:
        str: The text translated into Pig Latin.
    """
    return _WORD_PATTERN.sub(_PIG_LATIN_FORMAT, text)
