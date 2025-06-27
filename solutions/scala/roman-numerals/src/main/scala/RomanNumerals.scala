import scala.annotation.tailrec

object RomanNumerals {
  @tailrec
  def roman(number: Int, result: String = ""): String =
    number match
      case x if x >= 1000 => roman(number - 1000, result + "M")
      case x if x >= 900 => roman(number - 900, result + "CM")
      case x if x >= 500 => roman(number - 500, result + "D")
      case x if x >= 400 => roman(number - 400, result + "CD")
      case x if x >= 100 => roman(number - 100, result + "C")
      case x if x >= 90 => roman(number - 90, result + "XC")
      case x if x >= 50 => roman(number - 50, result + "L")
      case x if x >= 40 => roman(number - 40, result + "XL")
      case x if x > 9 => roman(x - 10, result + "X")
      case 9 => roman(number - 9, result + "IX")
      case 5 | 6 | 7 | 8 => roman(number - 5, result + "V")
      case 4 => roman(number - 4, result + "IV")
      case 1 | 2 | 3 => roman(number - 1, result + "I")
      case 0 => result
}