import itertools
import re

WORD_PATTERN = re.compile(r'[A-Z]+')

type Solution = dict[str, int]
type Hypothesis = tuple[int, ...]


def solve(puzzle: str) -> Solution | None:
    """Solves an alphametics puzzle.

    This function takes a string representing a cryptarithmetic puzzle
    (e.g., "SEND + MORE == MONEY") and finds a valid mapping of letters
    to digits (0-9) that satisfies the mathematical equation.

    Args:
        puzzle: A string representing the puzzle. Words should consist of
            uppercase letters and be separated by non-alphabetic characters.
            The final two words are expected to be separated by "==".

    Returns:
        A dictionary mapping each letter to its assigned integer value if a
        solution is found. For example, {'S': 9, 'E': 5, ...}.
        Returns None if no solution exists or if the puzzle is impossible
        (e.g., contains more than 10 unique letters).
    """
    words = WORD_PATTERN.findall(puzzle)
    unique_letters = sorted(tuple(set(''.join(words))))

    digits = range(10)
    if len(unique_letters) > len(digits):
        return None

    letter_to_index = {letter: i for i, letter in enumerate(unique_letters)}
    leading_letters = {word[0] for word in words if len(word) > 1}
    leading_indexes = {letter_to_index[letter] for letter in leading_letters}

    summands = tuple(word[::-1] for word in words[:-1])
    result = words[-1][::-1]
    max_length = len(result)

    if any(len(s) > max_length for s in summands):
        return None

    column_equations = tuple(
        (
            tuple(letter_to_index[word[i]] for word in summands if i < len(word)),
            letter_to_index[result[i]]
        )
        for i in range(max_length)
    )

    def is_valid(hypothesis: Hypothesis) -> bool:
        if any(hypothesis[i] == 0 for i in leading_indexes):
            return False

        carry = 0
        for summand_indices, result_index in column_equations:
            column_sum = carry + sum(hypothesis[i] for i in summand_indices)
            if hypothesis[result_index] != column_sum % 10:
                return False
            carry = column_sum // 10

        return carry == 0

    hypotheses_iterator = itertools.permutations(digits, len(unique_letters))
    solution_hypothesis = next(filter(is_valid, hypotheses_iterator), None)

    return dict(zip(unique_letters, solution_hypothesis)) if solution_hypothesis else None
