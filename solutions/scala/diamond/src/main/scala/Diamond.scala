object Diamond:
  def rows(widest: Char): List[String] =
    val line = (current: Char) =>
      lazy val innerSpaces = " " * (2 * (current - 'A') - 1)
      val outerSpaces = " " * (widest - current)
      if current == 'A' then outerSpaces + current + outerSpaces
      else outerSpaces + current + innerSpaces + current + outerSpaces

    val forwardSequence = ('A' to widest).toList
    val reverseSequence = ('A' until widest).toList.reverse
    forwardSequence ++ reverseSequence map line
