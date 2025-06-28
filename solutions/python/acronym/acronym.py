import re
WORD_PATTERN = re.compile(r"([^\W_])[\w']*[\W_]*")


def abbreviate(words: str) -> str:
    """Convert a given phrase or sentence to its acronym form.

    Args:
        words: The input string containing a phrase or sentence to be abbreviated.

    Returns:
        The acronym is formed by capitalizing the first letter of each word in the input.
    """
    return WORD_PATTERN.sub(lambda m: m.group(1).upper(), words)
