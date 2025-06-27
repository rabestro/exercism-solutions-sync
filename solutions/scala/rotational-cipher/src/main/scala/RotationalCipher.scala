object RotationalCipher:
  private val AlphabetSize = ('a' to 'z').size
  
  def rotate(text: String, key: Int): String =

    def shift(symbol: Char, first: Char): Char =
      (first + (symbol - first + key) % AlphabetSize).toChar

    def encode(symbol: Char): Char =
      if symbol.isLetter then
        if symbol.isLower then shift(symbol, 'a') 
        else shift(symbol, 'A')
      else symbol
      
    text.map(encode)  