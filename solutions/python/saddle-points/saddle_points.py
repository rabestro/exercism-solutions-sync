type Matrix = list[list[int]]
type Point = dict[str, int]


def saddle_points(matrix: Matrix) -> list[Point]:
    if not matrix:
        return []

    height = len(matrix)
    width = len(matrix[0])
    if any(len(row) != width for row in matrix[1:]):
        raise ValueError("irregular matrix")

    rows_maximum = tuple(max(row) for row in matrix)
    cols_minimum = tuple(min(col) for col in zip(*matrix))

    return [
        {'row': row + 1, 'column': col + 1}
        for row in range(height)
        for col in range(width)
        if rows_maximum[row] == cols_minimum[col]
    ]
