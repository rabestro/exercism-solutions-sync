CLEANING_TABLE = str.maketrans('-', ' ', '_')


def abbreviate(words: str) -> str:
    """Convert a given phrase or sentence to its acronym form.

    Args:
        words: The input string containing a phrase or sentence to be abbreviated.

    Returns:
        The acronym is formed by capitalizing the first letter of each word in the input.
    """
    cleaned_string = words.translate(CLEANING_TABLE)

    return "".join(
        word[0].upper()
        for word in cleaned_string.split()
    )
