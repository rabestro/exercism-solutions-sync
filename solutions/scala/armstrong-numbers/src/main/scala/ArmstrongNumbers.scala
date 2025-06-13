object ArmstrongNumbers {
  def isArmstrongNumber(number: Int): Boolean = {
    val digits = number.toString.map(_.asDigit)
    number == digits.map(math.pow(_, digits.size)).sum
  }
}