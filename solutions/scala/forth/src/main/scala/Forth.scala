import ForthError.ForthError

import scala.annotation.tailrec


case class State(stack: List[Int] = Nil, words: Map[String, List[String]] = Map.empty) extends ForthEvaluatorState:

  override def toString: String =
    stack.reverse.mkString(" ")

end State


class Forth extends ForthEvaluator:
  @tailrec
  private def loop(tokens: List[String], state: State): Either[ForthError, State] =
    tokens match
      case Nil =>
        Right(state)
      case "+" :: rest =>
        state.stack match
          case a :: b :: tail =>
            loop(rest, state.copy(stack = (b + a) :: tail))
          case _ =>
            Left(ForthError.StackUnderflow)

      case "-" :: rest =>
        state.stack match
          case a :: b :: tail =>
            loop(rest, state.copy(stack = (b - a) :: tail))
          case _ =>
            Left(ForthError.StackUnderflow)

      case "*" :: rest =>
        state.stack match
          case a :: b :: tail =>
            loop(rest, state.copy(stack = (b * a) :: tail))
          case _ =>
            Left(ForthError.StackUnderflow)

      case "/" :: rest =>
        state.stack match
          case 0 :: b :: tail =>
            Left(ForthError.DivisionByZero)
          case a :: b :: tail =>
            loop(rest, state.copy(stack = (b / a) :: tail))
          case _ =>
            Left(ForthError.StackUnderflow)

      case "dup" :: rest =>
        state.stack match
          case a :: tail =>
            loop(rest, state.copy(stack = a :: a :: tail))
          case _ =>
            Left(ForthError.StackUnderflow)

      case "drop" :: rest =>
        state.stack match
          case a :: tail =>
            loop(rest, state.copy(stack = tail))
          case _ =>
            Left(ForthError.StackUnderflow)

      case "swap" :: rest =>
        state.stack match
          case a :: b :: tail =>
            loop(rest, state.copy(stack = b :: a :: tail))
          case _ =>
            Left(ForthError.StackUnderflow)

      case "over" :: rest =>
        state.stack match
          case a :: b :: tail =>
            loop(rest, state.copy(stack = b :: a :: b :: tail))
          case _ =>
            Left(ForthError.StackUnderflow)

      case token :: rest =>
        token.toIntOption match
          case Some(n) =>
            loop(rest, state.copy(stack = n :: state.stack))
          case None =>
            Left(ForthError.UnknownWord)

  def eval(text: String): Either[ForthError, ForthEvaluatorState] =
    val tokens = text.trim.split("\\s+").filter(_.nonEmpty).toList
    loop(tokens, State())
