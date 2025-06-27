object RotationalCipher:
  private val AlphabetSize = ('a' to 'z').size

  def rotate(text: String, key: Int): String =

    def shift(letter: Char, base: Char): Char =
      (base + (letter - base + key) % AlphabetSize).toChar

    def encode(symbol: Char): Char =
      if !symbol.isLetter then symbol
      else if symbol.isLower then shift(symbol, 'a')
      else shift(symbol, 'A')

    text.map(encode)