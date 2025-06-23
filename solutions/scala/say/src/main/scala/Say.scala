object Say:
  private val MaxPronounceableNumber = 999_999_999_999L
  private val Billion = 1_000_000_000L
  private val Million = 1_000_000
  private val Thousand = 1_000

  def inEnglish(number: Long): Option[String] =
    Option(sayNumber(number))

  private def sayNumber(number: Long): String =
    def say(x: Long, s: String): String =
      sayNumber(number / x) + " " + s + " " + sayNumber(number % x)

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
      case 100 => "one hundred"
      case n if n < Thousand => say(100, "hundred")
      case Thousand => "one thousand"
      case n if n < Million => say(Thousand, "thousand")
      case Million => "one million"
      case n if n < Billion => say(Million, "million")
      case Billion => "one billion"
      case _ => say(Billion, "billion")

end Say