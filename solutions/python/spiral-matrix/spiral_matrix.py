def spiral_matrix(size: int) -> list[list[int]]:
    """Creates a square matrix of given size filled with numbers in spiral order.

    This function generates a square matrix where numbers are arranged in a clockwise
    spiral pattern starting from 1 in the top-left corner.

    Args:
        size: An integer representing both the number of rows and columns
            in the square matrix.

    Returns:
        list[list[int]]: A square matrix of size x size containing integers
            arranged in clockwise spiral order starting from 1.

    Example:
        >>> spiral_matrix(3)
        [[1, 2, 3],
         [8, 9, 4],
         [7, 6, 5]]
    """

    def construct_matrix(rows: int, columns: int, start: int) -> list[list[int]]:
        if rows == 0:
            return []
        top_row = list(range(start, start + columns))
        inner_matrix = reversed(construct_matrix(columns, rows - 1, start + columns))
        return [top_row] + [list(row) for row in zip(*inner_matrix)]

    return construct_matrix(size, size, 1)
