import scala.annotation.tailrec

object Alphametics:
  private type Hypothesis = List[Int]

  def solve(puzzle: String): Option[Map[Char, Int]] =
    val words = puzzle.split("\\W+").filter(_.nonEmpty)
    val uniqueLetters = words.flatten.distinct
    val letterToIndex = uniqueLetters.zipWithIndex.toMap

    val leadingLetters = words
      .filter(_.length > 1)
      .map(_.head)
      .distinct
      .map(letterToIndex)

    def hasNoLeadingZeros(hypothesis: Hypothesis): Boolean =
      !leadingLetters.map(hypothesis).contains(0)

    val lines = words.map(_.reverse).reverse
    val maxLength = lines.headOption.map(_.length).getOrElse(0)

    val matrix = (0 until maxLength)
      .map(i => lines.flatMap(_.lift(i) map letterToIndex))

    def isHypothesisValid(hypothesis: Hypothesis): Boolean =
      @tailrec
      def evaluateLevelConditions(level: Int, carry: Int): Boolean =
        if level == matrix.size then
          carry == 0
        else
          val sum = carry + matrix(level).tail.map(hypothesis).sum
          hypothesis(matrix(level).head) == sum % 10 &&
            evaluateLevelConditions(level + 1, sum / 10)
      end evaluateLevelConditions

      evaluateLevelConditions(0, 0)
    end isHypothesisValid

    val digits = (0 to 9).toList

    digits
      .combinations(uniqueLetters.length)
      .flatMap(_.permutations)
      .filter(hasNoLeadingZeros)
      .find(isHypothesisValid)
      .map(uniqueLetters.zip(_).toMap)

  end solve
end Alphametics
