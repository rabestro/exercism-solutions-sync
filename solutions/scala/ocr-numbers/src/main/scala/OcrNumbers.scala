case object OcrNumbers:
  private val Width = 3
  private val Height = 4
  private val Digits = List(
    " _ | ||_|   ", "     |  |   ", " _  _||_    ", " _  _| _|   ", "   |_|  |   ",
    " _ |_  _|   ", " _ |_ |_|   ", " _   |  |   ", " _ |_||_|   ", " _ |_| _|   ")

  def convert(input: List[String]): String =
    def extractSymbol(row: Int) = (col: Int) => input
      .slice(row * Height, row * Height + Height)
      .map(s => s.substring(Width * col, Width + Width * col))
      .mkString

    lazy val rows = input.size / Height
    lazy val cols = input.head.length / Width

    lazy val getLine = (row: Int) => (0 until cols)
      .map(extractSymbol(row))
      .map(Digits.indexOf(_))
      .map(index => if index >= 0 then index.toString else "?")
      .mkString

    if input.size % Height != 0 || input.exists(_.length % Width != 0) then "?"
    else (0 until rows).map(getLine).mkString(",")
