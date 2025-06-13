import scala.annotation.tailrec

object CollatzConjecture {
  def steps(number: Int): Option[Int] =
    @tailrec
    def iter(number: Int, step: Int): Option[Int] = number match
      case 1 => Some(step)
      case n if n < 1 => None
      case n if (n % 2) == 0 => iter(n / 2, step + 1)
      case n => iter(3 * n + 1, step + 1)

    iter(number, 0)
}