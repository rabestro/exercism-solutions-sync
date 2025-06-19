def is_isogram(string: str) -> bool:
    lowercase_string = list(filter(str.isalpha, string.lower()))
    return len(set(lowercase_string)) == len(lowercase_string)
