object Series:
  def slices(size: Int, series: String): Seq[Seq[Int]]  =
    series.map(_.asDigit).sliding(size).toSeq