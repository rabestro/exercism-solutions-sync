object Bob {
  def response(statement: String): String = {
    if statement.isBlank then "Fine. Be that way!"
    else if statement.matches("[^a-z]*[A-Z][^a-z]*\\?") then "Calm down, I know what I'm doing!"
    else if statement.matches(".*\\?\\s*") then "Sure."
    else if statement.matches("[^a-z]*[A-Z][^a-z]*") then "Whoa, chill out!"
    else "Whatever."
  }
}
