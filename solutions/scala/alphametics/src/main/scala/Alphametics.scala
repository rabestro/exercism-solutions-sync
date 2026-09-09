import scala.annotation.tailrec

object Alphametics:
  private type LetterIndex = Int
  private type Assignments = Map[Char, Int]
  private type Hypothesis = Vector[Int]

  private case class Column(
                             result: LetterIndex,
                             addends: Vector[LetterIndex]
                           )

  private case class Puzzle(
                             uniqueLetters: Vector[Char],
                             leadingLetters: Set[LetterIndex],
                             columns: Vector[Column]
                           )

  private def parsePuzzle(puzzle: String): Option[Puzzle] =
    puzzle.split("==").map(_.trim) match
      case Array(left, right) if left.nonEmpty && right.nonEmpty =>
        val addends = left.split("\\+").map(_.trim).filter(_.nonEmpty).toVector
        val result = right

        if addends.isEmpty || addends.exists(_.length > result.length) then
          return None

        val words = addends :+ result
        val uniqueLetters = words.flatten.distinct
        if uniqueLetters.length > 10 then return None

        val letterToIndex = uniqueLetters.zipWithIndex.toMap
        val leadingLetters = words.map(_.head).distinct.map(letterToIndex).toSet

        val reversedAddends = addends.map(_.reverse)
        val reversedResult = result.reverse

        val columns = reversedResult.indices.toVector.map { i =>
          val resIdx = letterToIndex(reversedResult(i))
          val addendIndices = reversedAddends.flatMap(_.lift(i).map(letterToIndex))
          Column(result = resIdx, addends = addendIndices)
        }

        Some(Puzzle(uniqueLetters, leadingLetters, columns))

      case _ => None
  end parsePuzzle

  private def solvePuzzle(puzzle: Puzzle): Option[Assignments] =
    val columns = puzzle.columns

    def isHypothesisValid(hypothesis: Hypothesis): Boolean =
      def columnSum(column: Column, carry: Int): Int =
        carry + column.addends.map(hypothesis).sum

      @tailrec
      def evaluateLevelConditions(position: Int, carry: Int): Boolean =
        if position == columns.length then
          carry == 0
        else
          val col = columns(position)
          val sum = columnSum(col, carry)
          val resultValue = hypothesis(col.result)
          (resultValue == sum % 10) && evaluateLevelConditions(position + 1, sum / 10)
      end evaluateLevelConditions

      evaluateLevelConditions(0, 0)
    end isHypothesisValid

    val digits = (0 to 9).toVector

    digits
      .combinations(puzzle.uniqueLetters.length)
      .flatMap(_.permutations)
      .filter(h => puzzle.leadingLetters.forall(h(_) != 0))
      .find(isHypothesisValid)
      .map(h => puzzle.uniqueLetters.zip(h).toMap)
  end solvePuzzle

  def solve(puzzle: String): Option[Assignments] =
    parsePuzzle(puzzle).flatMap(solvePuzzle)

end Alphametics