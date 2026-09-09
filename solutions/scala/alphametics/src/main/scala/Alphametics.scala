import scala.annotation.tailrec

object Alphametics:
  private type Hypothesis = List[Int]
  private type Assignments = Map[Char, Int]

  def solve(puzzle: String): Option[Assignments] =
    val words = puzzle.split("\\W+").filter(_.nonEmpty)
    val uniqueLetters = words.flatten.distinct
    val letterToIndex = uniqueLetters.zipWithIndex.toMap

    val leadingLetters = words
      .map(_.head).toSet
      .map(letterToIndex)

    val lines = words.map(_.reverse).reverse
    val maxLength = lines.headOption.map(_.length).getOrElse(0)

    val matrix = (0 until maxLength)
      .map(i => lines.flatMap(_.lift(i) map letterToIndex))

    def isHypothesisValid(hypothesis: Hypothesis): Boolean =
      @tailrec
      def evaluateLevelConditions(position: Int, carry: Int): Boolean =
        if position == matrix.size then
          carry == 0
        else
          val columnIndices = matrix(position)
          val columnSum = carry + columnIndices.tail.map(hypothesis).sum
          val resultValue = hypothesis(columnIndices.head)
          val satisfiesColumn = resultValue == columnSum % 10
          satisfiesColumn && evaluateLevelConditions(position + 1, columnSum / 10)
      end evaluateLevelConditions

      evaluateLevelConditions(0, 0)
    end isHypothesisValid

    val digits = (0 to 9).toList

    def hasLeadingZeros(hypothesis: Hypothesis): Boolean =
      leadingLetters.exists(hypothesis(_) == 0)

    digits
      .combinations(uniqueLetters.length)
      .flatMap(_.permutations)
      .filterNot(hasLeadingZeros)
      .find(isHypothesisValid)
      .map(uniqueLetters.zip(_).toMap)

  end solve
end Alphametics
