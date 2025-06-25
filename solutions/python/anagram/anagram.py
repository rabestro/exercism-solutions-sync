from collections import Counter


def find_anagrams(word: str, candidates: list[str]) -> list[str]:
    """Finds case-insensitive anagrams for a word from a list of candidates.

    This function identifies all strings in the candidates list that are
    anagrams of the target word. The comparison is case-insensitive, and the
    word itself is not considered an anagram. The returned list preserves the
    original casing of the matching candidates.

    Args:
        word (str): The target word for which to find anagrams.
        candidates (list[str]): A list of strings to check against the target word.

    Returns:
        list[str]: A list of all candidates that are anagrams of the word.
                   Returns an empty list if no anagrams are found.

    Examples:
        >>> find_anagrams("diaper", ["hello", "world", "zombies", "paired"])
        ['paired']

        >>> find_anagrams("Orchestra", ["Carthorse", "orchestra", "google"])
        ['Carthorse']

        >>> find_anagrams("allergy", ["gallery", "ballerina", "regally", "clergy", "largely", "leading"])
        ['gallery', 'regally', 'largely']

        >>> find_anagrams("good", ["dog", "goody"])
        []

        >>> find_anagrams("mass", [])
        []
    """
    lower_word = word.lower()
    word_counts = Counter(lower_word)

    return [
        candidate for candidate in candidates
        if (lower_candidate := candidate.lower()) != lower_word and
           Counter(lower_candidate) == word_counts
    ]
