object RotationalCipher:
  private val AlphabetSize = ('a' to 'z').size

  def rotate(text: String, key: Int): String =

    def shift(letter: Char) =
      val base = if letter.isLower then 'a' else 'A'
      (base + (letter - base + key) % AlphabetSize).toChar

    def encode(symbol: Char) =
      if symbol.isLetter then shift(symbol) else symbol

    text.map(encode)