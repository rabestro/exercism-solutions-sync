class Matrix:
    def __init__(self, matrix_string: str):
        self.matrix = [
            [int(n) for n in row.split()]
            for row in matrix_string.splitlines()
        ]

    def row(self, index):
        return self.matrix[index - 1]

    def column(self, index):
        return list(list(zip(*self.matrix, strict=True))[index - 1])
