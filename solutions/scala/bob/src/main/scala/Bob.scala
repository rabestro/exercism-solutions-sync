object Bob:
  def response(statement: String): String =
    val trimmed = statement.trim
    val letters = trimmed.filter(_.isLetter)
    val yelling = letters.nonEmpty && letters.forall(_.isUpper)
    val question = trimmed.endsWith("?")

    if yelling && question then
      "Calm down, I know what I'm doing!"
    else if yelling then
      "Whoa, chill out!"
    else if question then
      "Sure."
    else if trimmed.isEmpty then
      "Fine. Be that way!"
    else
      "Whatever."
