import scala.collection.parallel.CollectionConverters._
object Frequency {
  def frequency(numWorkers: Int, texts: Seq[String]): Map[Char, Int] =
    texts
      .par
      .flatMap(_.toSeq)
      .filter(_.isLetter)
      .map(_.toLower)
      .groupBy(identity)
      .mapValues(_.size)
      .seq
      .toMap
}
