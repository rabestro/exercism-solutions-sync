object Isogram {
  private val repeatingLetter = """(?i)(\p{L}).*?\1""".r

  def isIsogram(phrase: String): Boolean =
    repeatingLetter.findFirstIn(phrase).isEmpty
}
