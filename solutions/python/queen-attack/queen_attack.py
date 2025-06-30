class Queen:
    """Represents a queen piece on a chess board.

    The chess board is represented as an 8x8 grid with zero-based indices.
    Position is defined by row (0-7) and column (0-7) coordinates.
    """

    def __init__(self, row, column):
        """Initialize a queen with given position coordinates.

        Args:
            row: Integer representing the row position (0-7).
            column: Integer representing the column position (0-7).

        Raises:
            ValueError: If row or column is negative or greater than 7.
        """
        if row < 0:
            raise ValueError("row not positive")
        if column < 0:
            raise ValueError("column not positive")
        if row > 7:
            raise ValueError("row not on board")
        if column > 7:
            raise ValueError("column not on board")
        self.row = row
        self.column = column

    def can_attack(self, another_queen):
        """Determine if this queen can attack another queen.

        Args:
            another_queen: Queen instance representing another queen on the board.

        Returns:
            bool: True if queens can attack each other, False otherwise.

        Raises:
            ValueError: If both queens occupy the same position.
        """
        if self.row == another_queen.row and self.column == another_queen.column:
            raise ValueError("Invalid queen position: both queens in the same square")
        return (
                self.row == another_queen.row or
                self.column == another_queen.column or
                abs(self.row - another_queen.row) == abs(self.column - another_queen.column)
        )
