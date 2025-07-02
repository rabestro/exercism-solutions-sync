type Matrix = list[list[int]]
type Point = dict[str, int]


def saddle_points(matrix: Matrix) -> list[Point]:
    """Find saddle points in a given matrix.

    A saddle point is an element in the matrix that is both the largest value in its row
    and the smallest value in its column.

    Args:
        matrix (Matrix): A list of lists containing integers representing the grid of numbers.
            The matrix can be non-square but must be regular (all rows must have same length).

    Returns:
        list[Point]: A list of dictionaries containing the row and column indices (1-based)
            of all saddle points found in the matrix. Each dictionary has 'row' and 'column' keys.

    Raises:
        ValueError: If the matrix is irregular (rows have different lengths).

    Examples:
        >>> saddle_points([[9, 8, 7], [5, 3, 2], [6, 6, 7]])
        [{'row': 2, 'column': 1}]
    """
    if not matrix:
        return []

    height = len(matrix)
    width = len(matrix[0])
    if any(len(row) != width for row in matrix[1:]):
        raise ValueError("irregular matrix")

    rows_maximum = tuple(map(max, matrix))
    cols_minimum = tuple(min(col) for col in zip(*matrix))

    return [
        {'row': row + 1, 'column': col + 1}
        for row in range(height)
        for col in range(width)
        if rows_maximum[row] == cols_minimum[col]
    ]
