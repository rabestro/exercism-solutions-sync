def spiral_matrix(size: int) -> list[list[int]]:
    def construct_matrix(rows: int, columns: int, start: int) -> list[list[int]]:
        if rows == 0:
            return []
        else:
            top_row = list(range(start, start + columns))
            inner_matrix = construct_matrix(columns, rows - 1, start + columns)
            inner_matrix.reverse()
            return [top_row] + [list(row) for row in zip(*inner_matrix)]

    return construct_matrix(size, size, 1)
