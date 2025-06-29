import itertools
import re

WORD_PATTERN = re.compile(r'[A-Z]+')

type Solution = dict[str, int]


def solve(puzzle: str) -> Solution | None:
    words = WORD_PATTERN.findall(puzzle)
    # Use a sorted list for a stable, canonical ordering of letters
    unique_letters = sorted(list(set(''.join(words))))
    if len(unique_letters) > 10:
        return None  # More than 10 unique letters is impossible

    letter_to_index = {letter: i for i, letter in enumerate(unique_letters)}

    leading_letters = {word[0] for word in words if len(word) > 1}
    leading_indexes = {letter_to_index[letter] for letter in leading_letters}

    # --- Prepare data for validation ---
    summands = [word[::-1] for word in words[:-1]]
    result = words[-1][::-1]
    max_length = len(result)

    if any(len(s) > max_length for s in summands):
        return None  # A summand cannot be longer than the result

    def is_valid(hypothesis: tuple[int, ...]) -> bool:
        # Check for leading zeros first, a quick filter
        if any(hypothesis[i] == 0 for i in leading_indexes):
            return False

        carry = 0
        for i in range(max_length):
            column_sum = carry + sum(
                hypothesis[letter_to_index[word[i]]]
                for word in summands if i < len(word)
            )
            if i >= len(result) or hypothesis[letter_to_index[result[i]]] != column_sum % 10:
                return False
            carry = column_sum // 10

        return carry == 0

    digits = range(10)
    hypotheses_iterator = itertools.permutations(digits, len(unique_letters))
    solution_hypothesis = next(filter(is_valid, hypotheses_iterator), None)

    return dict(zip(unique_letters, solution_hypothesis)) if solution_hypothesis else None