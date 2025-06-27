object Minesweeper:
  def annotate(field: List[String]): List[String] =
    val rows = field.length
    val cols = field.headOption.map(_.length).getOrElse(0)

    def mines(row: Int, col: Int) = (for (
        y <- row - 1 to row + 1 if y >= 0 && y < field.length;
        x <- col - 1 to col + 1 if x >= 0 && x < field(y).length && field(y)(x) == '*'
      ) yield 1).sum

    def annotateCell(row: Int, col: Int): String =
      if field(row)(col) == '*' then "*"
      else mines(row, col) match
        case 0 => " "
        case n => n.toString

    def annotateRow(row: Int): String =
      (0 until cols).map(annotateCell(row, _)).mkString

    (0 until rows).map(annotateRow).toList


