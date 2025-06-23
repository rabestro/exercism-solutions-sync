object Say:
  private val MaxPronounceableNumber = 999_999_999_999L
  private val Billion = 1_000_000_000L
  private val Million = 1_000_000
  private val Thousand = 1_000

  def inEnglish(number: Long): Option[String] =
    Option(sayNumber(number))

  private def sayNumber(number: Long): String =
    def sayMagnitude(divisor: Long, name: String) =
      val quotientPart = sayNumber(number / divisor) + " " + name
      number % divisor match
        case 0 => quotientPart
        case remainder => quotientPart + " " + sayNumber(remainder)

    number match
      case x if x < 0 || MaxPronounceableNumber < x => null
      case 0 => "zero"
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case 4 => "four"
      case 5 => "five"
      case 6 => "six"
      case 7 => "seven"
      case 8 => "eight"
      case 9 => "nine"
      case 10 => "ten"
      case 11 => "eleven"
      case 12 => "twelve"
      case 13 => "thirteen"
      case 14 => "fourteen"
      case 15 => "fifteen"
      case 16 => "sixteen"
      case 17 => "seventeen"
      case 18 => "eighteen"
      case 19 => "nineteen"
      case 20 => "twenty"
      case 30 => "thirty"
      case 40 => "forty"
      case 50 => "fifty"
      case 60 => "sixty"
      case 70 => "seventy"
      case 80 => "eighty"
      case 90 => "ninety"
      case n if n < 100 => sayNumber(n / 10 * 10) + "-" + sayNumber(n % 10)
      case n if n < Thousand => sayMagnitude(100, "hundred")
      case n if n < Million => sayMagnitude(Thousand, "thousand")
      case n if n < Billion => sayMagnitude(Million, "million")
      case _ => sayMagnitude(Billion, "billion")
  end sayNumber
end Say