import string


def rows(widest_letter: str) -> list[str]:
    widest = ord(widest_letter)

    def line(current_letter: str) -> str:
        current = ord(current_letter)
        outer_spaces = " " * (widest - current)
        if current == ord('A'):
            return outer_spaces + current_letter + outer_spaces
        else:
            inner_spaces = " " * (2 * (current - ord('A')) - 1)
            return outer_spaces + current_letter + inner_spaces + current_letter + outer_spaces

    index = string.ascii_uppercase.index(widest_letter)
    forward_sequence = string.ascii_uppercase[:index]
    reverse_sequence = forward_sequence[::-1]
    sequence = forward_sequence + widest_letter + reverse_sequence
    return [line(letter) for letter in sequence]
