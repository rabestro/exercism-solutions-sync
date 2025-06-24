object CryptoSquare:
  private val normalize = (text: String) =>
    text.filter(_.isLetterOrDigit).toLowerCase

  private val transpose = (normalizedText: String) =>
    val chunkSize = math.ceil(math.sqrt(normalizedText.length)).toInt
    normalizedText
      .grouped(chunkSize).toList
      .map(_.padTo(chunkSize, ' '))
      .transpose
      .map(_.mkString)
      .mkString(" ")

  val ciphertext: String => String = normalize.andThen(transpose)
