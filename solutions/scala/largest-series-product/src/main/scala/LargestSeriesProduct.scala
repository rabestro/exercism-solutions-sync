object Series:
  def largestProduct(span: Int, digits: String): Option[Int] =
    if span < 1 || span > digits.length || digits.exists(!_.isDigit) then None
    else digits.map(_.asDigit).sliding(span).map(_.product).maxOption

