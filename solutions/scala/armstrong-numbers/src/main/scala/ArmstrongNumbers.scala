import scala.annotation.tailrec

object ArmstrongNumbers {
  @tailrec
  private def extractDigits(n: Int, acc: List[Int] = Nil): List[Int] =
    if n == 0 then
      if acc.isEmpty then List(0) else acc
    else extractDigits(n / 10, (n % 10) :: acc)

  def pow(exp: Int)(base: Int): Int =
    (1 to exp).fold(1)((a, *) => a * base)

  def isArmstrongNumber(number: Int): Boolean =
    val digits = extractDigits(number)
    val powerToSize = pow(digits.size)
    number == (digits map powerToSize).sum
}
