object Bob {
  def response(statement: String): String = statement.trim match {
    case "" ⇒ "Fine. Be that way!"
    case s if s.matches("[^a-z]*[A-Z][^a-z]*") => 
      if s.endsWith("?") then "Calm down, I know what I'm doing!" 
      else "Whoa, chill out!"
    case s if s.endsWith("?") ⇒ "Sure."
    case _ ⇒ "Whatever."
  }
}