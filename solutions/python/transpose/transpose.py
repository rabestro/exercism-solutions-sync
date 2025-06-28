from itertools import zip_longest


def transpose(text: str) -> str:
    lines = text.splitlines()
    max_width = 0
    for i in range(len(lines) - 1, -1, -1):
        max_width = max(max_width, len(lines[i]))
        lines[i] = lines[i].ljust(max_width)

    return '\n'.join(''.join(c) for c in zip_longest(*lines, fillvalue=''))
