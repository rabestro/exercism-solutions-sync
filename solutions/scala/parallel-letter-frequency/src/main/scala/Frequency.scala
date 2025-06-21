object Frequency {
  def frequency(numWorkers: Int, texts: Seq[String]): Map[Char, Int] =
    texts
      .flatMap(_.toSeq)
      .filter(_.isLetter)
      .map(_.toLower)
      .groupBy(identity)
      .view
      .mapValues(_.size)
      .toMap
}
