object CollatzConjecture:

  def steps(number: Int): Option[Int] =
    @scala.annotation.tailrec
    def loop(number: Int, step: Int): Int =
      number match
        case 1 => step
        case x if x % 2 == 0 => loop(x / 2, step + 1)
        case x => loop(3 * x + 1, step + 1)

    Option.when(number > 0):
      loop(number, 0)
