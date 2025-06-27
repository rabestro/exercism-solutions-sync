import scala.annotation.tailrec

object RomanNumerals {
  private val romanNumerals = Seq(
    1000 -> "M", 900 -> "CM", 500 -> "D", 400 -> "CD",
    100 -> "C", 90 -> "XC", 50 -> "L", 40 -> "XL",
    10 -> "X", 9 -> "IX", 5 -> "V", 4 -> "IV", 1 -> "I"
  )

  @tailrec
  private def convert(remaining: Int, acc: List[String] = Nil): String =
    romanNumerals.find { case (value, _) => remaining >= value } match
      case None => acc.reverse.mkString
      case Some((value, numeral)) => convert(remaining - value, numeral :: acc)

  def roman(number: Int): String =
    require(number > 0 && number <= 3999, "Number must be in the range 1 to 3999")
    convert(number)
}