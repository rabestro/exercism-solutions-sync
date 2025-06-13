object Bob:
  def response(statement: String): String =
    val trimmed = statement.trim
    val isQuestion = trimmed.endsWith("?")
    val isYelling =
      val letters = trimmed.filter(_.isLetter)
      letters.nonEmpty && letters.forall(_.isUpper)

    trimmed match
      case _ if isYelling && isQuestion =>
        "Calm down, I know what I'm doing!"
      case _ if isYelling =>
        "Whoa, chill out!"
      case _ if isQuestion =>
        "Sure."
      case "" =>
        "Fine. Be that way!"
      case _ =>
        "Whatever."
