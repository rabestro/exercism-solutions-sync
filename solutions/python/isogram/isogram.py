def is_isogram(phrase: str) -> bool:
    scrubbed = list(filter(str.isalpha, phrase.lower()))
    return len(set(scrubbed)) == len(scrubbed)
