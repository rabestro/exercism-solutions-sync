def annotate(garden: list[str]) -> list[str]:
    """
    Adds flower counts to empty squares in a Flower Field garden.
    """
    if not garden:
        return []

    height = len(garden)
    width = len(garden[0])

    if any(len(row) != width or
           any(char not in ' *' for char in row)
           for row in garden):
        raise ValueError('The board is invalid with current input.')

    def count_flowers(row: int, col: int) -> str:
        if garden[row][col] == '*':
            return '*'
        flowers = sum(
            garden[r][c] == '*'
            for r in range(row - 1, row + 2) if 0 <= r < height
            for c in range(col - 1, col + 2) if 0 <= c < width
        )
        return str(flowers) if flowers else ' '

    return [
        ''.join(count_flowers(row, col) for col in range(width))
        for row in range(height)
    ]
