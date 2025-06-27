case class Matrix(matrix: List[List[Int]]):
  lazy val saddlePoints: Set[(Int, Int)] =
    val coordinates = for {
      row <- 0 until rows
      col <- 0 until cols
      if rowsMaximum(row) == colsMinimum(col)
    } yield (row, col)
    coordinates.toSet

  private lazy val rows = matrix.size
  private lazy val cols = if rows > 0 then matrix.head.size else 0
  private lazy val rowsMaximum = matrix.map(_.max)
  private lazy val colsMinimum = matrix.transpose.map(_.min)
