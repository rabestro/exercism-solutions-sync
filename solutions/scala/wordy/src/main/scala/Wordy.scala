import scala.annotation.tailrec

object Wordy:
  private val Expression = """(\D+) (-?\d+)(\D.*)""".r

  def answer(question: String): Option[Int] =

    @tailrec
    def evaluateRecursive(remainingInput: String, currentResult: Int): Option[Int] =
      remainingInput.trim match
        case "?" => Some(currentResult)
        case Expression(operationWord, numberString, remainingTail) =>
          val number = numberString.toInt
          operationWord match
            case "What is" => evaluateRecursive(remainingTail, number)
            case "plus" => evaluateRecursive(remainingTail, currentResult + number)
            case "minus" => evaluateRecursive(remainingTail, currentResult - number)
            case "multiplied by" => evaluateRecursive(remainingTail, currentResult * number)
            case "divided by" => evaluateRecursive(remainingTail, currentResult / number)
        case _ => None

    evaluateRecursive(question, 0)


