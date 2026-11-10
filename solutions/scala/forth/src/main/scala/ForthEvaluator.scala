import ForthError.ForthError

object ForthError extends Enumeration:
  type ForthError = Value
  val DivisionByZero, StackUnderflow, InvalidWord, UnknownWord = Value


trait ForthEvaluatorState:
  override def toString: String


abstract class Definition:
  def evaluate(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState]


trait ForthEvaluator:
  def eval(text: String): Either[ForthError, ForthEvaluatorState]
