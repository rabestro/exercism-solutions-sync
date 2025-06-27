object AtbashCipher:
  private def cipher(symbol: Char): Char =
    if symbol.isLetter then
      ('a' + 'z' - symbol.toLower).toChar
    else
      symbol

  def encode(plaintext: String): String =
    plaintext
      .filter(_.isLetterOrDigit)
      .map(cipher)
      .grouped(5)
      .mkString(" ")

  def decode(encodedText: String): String =
    encodedText.filterNot(_.isWhitespace).map(cipher)
