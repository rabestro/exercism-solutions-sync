object Luhn:
  def valid(candidate: String): Boolean =
    candidate
      .filterNot(_.isWhitespace)
      .reverse.map(_.asDigit).zipWithIndex
      .map((digit, index) => digit * (1 + index % 2))
      .map(x => if x > 9 then x - 9 else x)
      .sum % 10 == 0 && candidate.trim.length > 1
