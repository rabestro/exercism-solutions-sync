object ArmstrongNumbers {
  def isArmstrongNumber(number: Int): Boolean = {
    val power = number.toString.length
    val result = number.toString.map(_.asDigit).map(digit => math.pow(digit, power)).sum
    result == number
  }
}