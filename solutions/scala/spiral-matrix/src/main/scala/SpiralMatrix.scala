object SpiralMatrix:
  def spiralMatrix(size: Int, start: Int = 1): List[List[Int]] = size match
    case 0 => Nil
    case 1 => List(List(start))
    case _ =>
      val step = size - 1
      val innerMatrix = spiralMatrix(size - 2, start + step * 4)
      val middleRows = 1 until step map { i =>
        val left = start + step * 4 - i
        val right = start + step + i
        left :: innerMatrix(i - 1) ::: right :: Nil
      }
      val topRow = start to start + step
      val bottomRow = start + step * 3 to start + step * 2 by -1
      topRow.toList :: middleRows.toList ::: bottomRow.toList :: Nil
