from collections import Counter
import re

WORD_PATTERN = re.compile(r"[a-zA-Z]+(?:'[a-zA-Z]+)*|-?\d+(?:\.\d+)?")


def count_words(sentence: str) -> dict[str, int]:
    """Count occurrences of each word in a given sentence.

    This function counts words in a case-insensitive manner, handling contractions,
    numbers, and various forms of punctuation and whitespace as word separators.

    Args:
        sentence: A string containing the text to analyze.

    Returns:
        A dictionary mapping words to their frequency counts, where:
        - Words are case-insensitive
        - Contractions (e.g., "don't") are counted as single words
        - Numbers are considered words
        - Words can be separated by any punctuation or whitespace
    """
    return dict(Counter(WORD_PATTERN.findall(sentence.lower())).items())
