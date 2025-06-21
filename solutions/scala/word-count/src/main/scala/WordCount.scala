case class WordCount(text: String):
  private val WordPattern = raw"\b[\p{Alnum}']+\b".r

  def countWords: Map[String, Int] =
    WordPattern
      .findAllIn(text.toLowerCase).toSeq
      .groupMapReduce(identity)(_ => 1)(_ + _)
