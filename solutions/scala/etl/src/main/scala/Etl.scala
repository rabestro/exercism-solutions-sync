object Etl {
  def transform(scoreMap: Map[Int, Seq[String]]): Map[String, Int] =
    val result = for {
      score <- scoreMap.keySet
      letter <- scoreMap(score)
    } yield letter.toLowerCase -> score
    result.toMap
}
