import re

_OPERATIONS = {
    "What is": lambda x, y: y,
    "plus": lambda x, y: x + y,
    "minus": lambda x, y: x - y,
    "multiplied by": lambda x, y: x * y,
    "divided by": lambda x, y: x // y,
}
_OPERATION_PATTERN = r"(?P<operation>\w+(?: (?:by|is))?)(?P<number> -?\d+)?"


def answer(question: str) -> int:
    result = 0
    for match in re.finditer(_OPERATION_PATTERN, question):
        operation = match.group('operation')
        number = match.group('number')

        if operation == "cubed":
            raise ValueError("unknown operation")
        if not operation or not number:
            raise ValueError("syntax error")
        # if operation not in _OPERATIONS:
        #     raise ValueError("unknown operation")

        result = _OPERATIONS[operation](result, int(number))

    return result
