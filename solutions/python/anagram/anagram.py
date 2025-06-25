from collections import Counter

def find_anagrams(word: str, candidates: list[str]) -> list[str]:
    lower_word = word.lower()
    word_counts = Counter(lower_word)

    return [
        candidate for candidate in candidates
        if candidate.lower() != lower_word and
           Counter(candidate.lower()) == word_counts
    ]
