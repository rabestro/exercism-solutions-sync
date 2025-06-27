import scala.annotation.tailrec

object RomanNumerals {
  private val romanNumerals = Seq(
    1000 -> "M", 900 -> "CM", 500 -> "D", 400 -> "CD",
    100 -> "C", 90 -> "XC", 50 -> "L", 40 -> "XL",
    10 -> "X", 9 -> "IX", 5 -> "V", 4 -> "IV", 1 -> "I"
  )

  @tailrec
  def roman(number: Int, result: String = ""): String =
    romanNumerals.find { case (value, _) => number >= value } match
      case None => result
      case Some((value, numeral)) => roman(number - value, result + numeral)
}