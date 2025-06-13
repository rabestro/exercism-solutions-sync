object Bob {
  private val isYelling = "[^a-z]*[A-Z][^a-z]*".r
  private val isQuestion: String => Boolean = _.endsWith("?")

  def response(statement: String): String = statement.trim match {
    case s @ isYelling() if isQuestion(s) =>
      "Calm down, I know what I'm doing!"
    case isYelling() =>
      "Whoa, chill out!"
    case s if isQuestion(s) =>
      "Sure."
    case "" =>
      "Fine. Be that way!"
    case _ =>
      "Whatever."
  }
}
