import scala.annotation.tailrec

object CollatzConjecture:

  @tailrec
  def steps(number: Int, step: Int = 0): Option[Int] =
    if number < 1 then None
    else number match
      case 1 => Some(step)
      case x if x % 2 == 0 => steps(x / 2, step + 1)
      case x => steps(3 * x + 1, step + 1)
