object Bob {
  def response(statement: String): String = statement.trim match {
    case "" ⇒ "Fine. Be that way!"
    case s if s.matches("[^a-z]*[A-Z][^a-z]*\\?") => "Calm down, I know what I'm doing!"
    case s if s.endsWith("?") ⇒ "Sure."
    case s if s.matches("[^a-z]*[A-Z][^a-z]*") => "Whoa, chill out!"
    case _ ⇒ "Whatever."
  }
}