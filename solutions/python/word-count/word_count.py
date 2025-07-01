from collections import Counter
import re
WORD_PATTERN = re.compile(r"[a-zA-Z]+(?:'[a-zA-Z]+)*|-?\d+(?:\.\d+)?")
def count_words(sentence: str) -> dict[str, int]:
    return dict(Counter(WORD_PATTERN.findall(sentence.lower())).items())
