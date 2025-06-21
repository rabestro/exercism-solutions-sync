case class WordCount(text: String):
  private val WordPattern = raw"\b[\p{Alnum}']+\b".r

  def countWords: Map[String, Int] =
    WordPattern
      .findAllIn(text.toLowerCase)
      .foldLeft(Map.empty[String, Int]) {
        case (map, word) => map + (word -> (map.getOrElse(word, 0) + 1))
      }
