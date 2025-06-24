object CryptoSquare:
  private val normalize = (text: String) =>
    text.filter(_.isLetterOrDigit).toLowerCase

  private val transpose = (normalizedText: String) =>
    val length = normalizedText.length
    val side = math.sqrt(length).toInt
    val cols = if side * side < length then side + 1 else side
    val rows = if side * cols < length then side + 1 else side
    val remainder = rows * cols - length
    val expandedText = normalizedText + " " * remainder
    expandedText.grouped(cols).toList
      .transpose
      .map(_.mkString)
      .mkString(" ")

  def ciphertext(plaintext: String): String =
    normalize.andThen(transpose).apply(plaintext)