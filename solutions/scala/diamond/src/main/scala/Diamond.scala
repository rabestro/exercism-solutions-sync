object Diamond:
  private def line(widest: Char)(current: Char) =
    val outerSpaces = " " * (widest - current)
    if current == 'A' then
      outerSpaces + current + outerSpaces
    else
      val innerSpaces = " " * (2 * (current - 'A') - 1)
      outerSpaces + current + innerSpaces + current + outerSpaces

  def rows(widest: Char): List[String] =
    val topLetters = ('A' to widest).toList
    val bottomLetters = ('A' until widest).toList.reverse
    topLetters ++ bottomLetters map line(widest)
