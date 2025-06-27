object Series:
  def slices(size: Int, series: String): List[List[Int]] =
    series.sliding(size).map(_.map(_.asDigit).toList).toList