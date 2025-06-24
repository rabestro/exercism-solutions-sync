_OPENING_BRACKETS = "({["
_CLOSING_BRACKETS = ")}]"
_BRACKETS = _OPENING_BRACKETS + _CLOSING_BRACKETS

def is_matching(a, b):
    return _OPENING_BRACKETS.index(a) == _CLOSING_BRACKETS.index(b)

def is_paired(input_string: str) -> bool:
    brackets = (c for c in input_string if c in _BRACKETS)
    stack = []
    for bracket in brackets:
        if bracket in _OPENING_BRACKETS:
            stack.append(bracket)
        elif not stack or not is_matching(stack.pop(), bracket):
            return False

    return not stack
