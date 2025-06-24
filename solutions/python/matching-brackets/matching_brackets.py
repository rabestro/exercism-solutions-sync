from collections import deque

BRACKET_MAP = {')': '(', '}': '{', ']': '['}
OPENING_BRACKETS = set(BRACKET_MAP.values())


def is_paired(input_string: str) -> bool:
    stack = deque()
    for char in input_string:
        if char in OPENING_BRACKETS:
            stack.append(char)
        elif char in BRACKET_MAP:
            if not stack or stack.pop() != BRACKET_MAP[char]:
                return False

    return not stack
