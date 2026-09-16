object KillerSudokuHelper:
  private val MaxDigit = 9

  def combinations(sum: Int, size: Int, exclude: List[Int] = Nil): List[List[Int]] =
    val excluded = exclude.toSet
    val availableDigits = (1 to MaxDigit).filterNot(excluded)

    availableDigits
      .combinations(size)
      .filter(_.sum == sum)
      .map(_.toList)
      .toList