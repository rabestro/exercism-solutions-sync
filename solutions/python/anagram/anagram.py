def find_anagrams(word: str, candidates: list[str]) -> list[str]:
    sorted_word = sorted(word := word.lower())

    def is_anagram(candidate: str) -> bool:
        return (len(candidate) == len(word)
                and (candidate := candidate.lower()) != word
                and sorted(candidate) == sorted_word)

    return list(filter(is_anagram, candidates))
