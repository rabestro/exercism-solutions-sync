from operator import add, mul, sub, floordiv
import re

_OPERATIONS = {
    "What is": add,
    "plus": add,
    "minus": sub,
    "multiplied by": mul,
    "divided by": floordiv,
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
