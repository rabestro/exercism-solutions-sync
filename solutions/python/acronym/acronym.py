import re
WORD_PATTERN = re.compile(r"([^\W_])[\w']*[\W_]*")


def abbreviate(words: str) -> str:
    return WORD_PATTERN.sub(lambda m: m.group(1).upper(), words)
