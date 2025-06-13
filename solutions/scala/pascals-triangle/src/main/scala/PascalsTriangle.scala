object PascalsTriangle {
  def rows(level: Int): List[List[Int]] = level match
      case row if row < 1 => List()
      case 1 => List(List(1))
      case x =>
        val top = rows(x - 1)
        val lastLine = top.reverse.head
        val nextLine = for {
          i <- 1 until x - 1
        } yield lastLine(i - 1) + lastLine(i)

        top :+ ((1 :: nextLine.toList) :+ 1)
}