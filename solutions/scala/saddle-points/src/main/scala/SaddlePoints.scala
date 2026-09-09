case class Matrix(matrix: List[List[Int]]):
  lazy val saddlePoints: Set[(Int, Int)] =
    if matrix.isEmpty || matrix.head.isEmpty then Set.empty
    else
      val rows = matrix.size
      val cols = matrix.head.size
      val rowMax = matrix.map(_.max).toVector
      val colMin = matrix.transpose.map(_.min).toVector

      val coordinates = for {
        row <- 0 until rows
        col <- 0 until cols
        if rowMax(row) == colMin(col)
      } yield (row, col)

      coordinates.toSet
