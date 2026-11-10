import ForthError.ForthError

import scala.annotation.tailrec


case class State(stack: List[Int] = Nil, words: Map[String, List[String]] = Map.empty) extends ForthEvaluatorState:
  override def toString: String =
    stack.reverse.mkString(" ")

class Forth extends ForthEvaluator:
  @tailrec
  private def loop(tokens: List[String], state: State): Either[ForthError, State] =
    tokens match
      case Nil => Right(state)

      case ":" :: rest =>
        val (definitionTokens, afterSemicolon) = rest.span(_ != ";")
        definitionTokens match
          case name :: _ if name.toIntOption.isDefined =>
            Left(ForthError.InvalidWord)
          case name :: body =>
            val expandedBody = body.flatMap(token => state.words.getOrElse(token, List(token)))
            loop(afterSemicolon.drop(1), state.copy(words = state.words + (name -> expandedBody)))
          case Nil =>
            Left(ForthError.InvalidWord)

      case token :: rest if state.words.contains(token) =>
        loop(state.words(token) ++ rest, state)

      case token :: rest =>
        token.toIntOption match
          case Some(n) =>
            loop(rest, state.copy(stack = n :: state.stack))
          case None =>
            token match
              case "+" => state.stack match
                case a :: b :: tail => loop(rest, state.copy(stack = (b + a) :: tail))
                case _ => Left(ForthError.StackUnderflow)

              case "-" => state.stack match
                case a :: b :: tail => loop(rest, state.copy(stack = (b - a) :: tail))
                case _ => Left(ForthError.StackUnderflow)

              case "*" => state.stack match
                case a :: b :: tail => loop(rest, state.copy(stack = (b * a) :: tail))
                case _ => Left(ForthError.StackUnderflow)

              case "/" => state.stack match
                case 0 :: b :: tail => Left(ForthError.DivisionByZero)
                case a :: b :: tail => loop(rest, state.copy(stack = (b / a) :: tail))
                case _ => Left(ForthError.StackUnderflow)

              case "dup" => state.stack match
                case a :: tail => loop(rest, state.copy(stack = a :: a :: tail))
                case _ => Left(ForthError.StackUnderflow)

              case "drop" => state.stack match
                case a :: tail => loop(rest, state.copy(stack = tail))
                case _ => Left(ForthError.StackUnderflow)

              case "swap" => state.stack match
                case a :: b :: tail => loop(rest, state.copy(stack = b :: a :: tail))
                case _ => Left(ForthError.StackUnderflow)

              case "over" => state.stack match
                case a :: b :: tail => loop(rest, state.copy(stack = b :: a :: b :: tail))
                case _ => Left(ForthError.StackUnderflow)

              case _ => Left(ForthError.UnknownWord)

  def eval(text: String): Either[ForthError, ForthEvaluatorState] =
    val tokens = text.trim.toLowerCase.split("\\s+").filter(_.nonEmpty).toList
    loop(tokens, State())
