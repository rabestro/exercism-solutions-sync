object RailFenceCipher:
  private def encodeSequence(rails: Int, length: Int): Seq[Int] =
    val maxRow = rails - 1
    val maxCol = length - 1
    val step = 2 * maxRow
    val top = 0 to maxCol by step
    val bottom = maxRow to maxCol by step
    val inners = for {
      row <- 1 until maxRow
      first <- row to maxCol by step
      second = first + 2 * (maxRow - row)
      index <- if second <= maxCol then Seq(first, second) else Seq(first)
    } yield index
    top ++ inners ++ bottom

  def encode(plainText: String, rails: Int): String =
    encodeSequence(rails, plainText.length).map(plainText).mkString

  def decode(encodedText: String, rails: Int): String =
    val plainText = new Array[Char](encodedText.length)
    encodeSequence(rails, encodedText.length).zipWithIndex
      .foreach { case (index, i) => plainText(index) = encodedText(i) }
    plainText.mkString



