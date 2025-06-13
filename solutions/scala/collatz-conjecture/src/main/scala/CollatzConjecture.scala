import scala.annotation.tailrec

object CollatzConjecture {
  /**
   * Computes the number of steps required to reach 1 in the Collatz Conjecture.
   *
   * @param number The starting number (must be positive).
   * @param step The current step count (default is 0, used for recursion).
   * @return The number of steps to reach 1, or None if the input is invalid.
   */
  @tailrec
  def steps(number: Int, step: Int = 0): Option[Int] =
    if (number < 1) None
    else number match {
      case 1 => Some(step)
      case x if x % 2 == 0 => steps(x / 2, step + 1)
      case x => steps(3 * x + 1, step + 1)
    }
}