object Pangrams {
  private val AlphabetSize = ('A' to 'Z').size
  def isPangram(phrase: String): Boolean =
    phrase
      .filter(_.isLetter)
      .map(_.toLower)
      .distinct
      .take(AlphabetSize)
      .length == AlphabetSize
}

