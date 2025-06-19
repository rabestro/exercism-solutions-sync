from string import ascii_lowercase


def is_pangram(sentence: str) -> bool:
    return (set(ascii_lowercase).issubset(sentence.lower()))
