object SpiralMatrix:
  def spiralMatrix(size: Int): List[List[Int]] =
    def constructMatrix(rows: Int, columns: Int, start: Int): List[List[Int]] =
      if rows == 0 then Nil
      else
        val next = start until start + columns
        val prev = constructMatrix(columns, rows - 1, start + columns)
        next.toList :: prev.reverse.transpose

    constructMatrix(size, size, 1)
