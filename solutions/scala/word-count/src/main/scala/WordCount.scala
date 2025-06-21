case class WordCount(text: String):
  private val WordDelimiter = """([^a-zA-Z0-9']|(?<=\W)'|'(?=\W))+"""

  def countWords: Map[String, Int] = {
    text.toLowerCase
      .split(WordDelimiter)
      .filterNot(_.isEmpty)
      .groupBy(identity)
      .view.mapValues(_.length)
      .toMap
  }
