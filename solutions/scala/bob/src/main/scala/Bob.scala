object Bob {
  private val isYelling = "[^a-z]*[A-Z][^a-z]*".r

  def response(statement: String): String = statement.trim match {
    case s @ isYelling() if s.endsWith("?") =>
      "Calm down, I know what I'm doing!"
    case isYelling() =>
      "Whoa, chill out!"
    case s if s.endsWith("?") =>
      "Sure."
    case "" =>
      "Fine. Be that way!"
    case _ =>
      "Whatever."
  }
}
