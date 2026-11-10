import ForthError.ForthError

import scala.annotation.tailrec


case class State(stack: List[Int] = Nil, words: Map[String, List[String]] = Map.empty) extends ForthEvaluatorState:
  override def toString: String = stack.reverse.mkString(" ")

  def push(n: Int): State = copy(stack = n :: stack)

  def add: Either[ForthError, State] = stack match
    case a :: b :: tail => Right(copy(stack = (b + a) :: tail))
    case _ => Left(ForthError.StackUnderflow)

  def sub: Either[ForthError, State] = stack match
    case a :: b :: tail => Right(copy(stack = (b - a) :: tail))
    case _ => Left(ForthError.StackUnderflow)

  def mul: Either[ForthError, State] = stack match
    case a :: b :: tail => Right(copy(stack = (b * a) :: tail))
    case _ => Left(ForthError.StackUnderflow)

  def div: Either[ForthError, State] = stack match
    case 0 :: _ :: _ => Left(ForthError.DivisionByZero)
    case a :: b :: tail => Right(copy(stack = (b / a) :: tail))
    case _ => Left(ForthError.StackUnderflow)

  def dup: Either[ForthError, State] = stack match
    case a :: tail => Right(copy(stack = a :: a :: tail))
    case _ => Left(ForthError.StackUnderflow)

  def drop: Either[ForthError, State] = stack match
    case _ :: tail => Right(copy(stack = tail))
    case _ => Left(ForthError.StackUnderflow)

  def swap: Either[ForthError, State] = stack match
    case a :: b :: tail => Right(copy(stack = b :: a :: tail))
    case _ => Left(ForthError.StackUnderflow)

  def over: Either[ForthError, State] = stack match
    case a :: b :: tail => Right(copy(stack = b :: a :: b :: tail))
    case _ => Left(ForthError.StackUnderflow)
end State


class Forth extends ForthEvaluator:
  @tailrec
  private def loop(tokens: List[String], state: State): Either[ForthError, State] =
    tokens match
      case Nil => Right(state)

      case ":" :: rest =>
        val (definitionTokens, afterSemicolon) = rest.span(_ != ";")
        if afterSemicolon.isEmpty then Left(ForthError.InvalidWord)
        else definitionTokens match
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
            loop(rest, state.push(n))
          case None =>
            val stepResult = token match
              case "+" => state.add
              case "-" => state.sub
              case "*" => state.mul
              case "/" => state.div
              case "dup" => state.dup
              case "drop" => state.drop
              case "swap" => state.swap
              case "over" => state.over
              case _ => Left(ForthError.UnknownWord)

            stepResult match
              case Right(nextState) => loop(rest, nextState)
              case Left(err)        => Left(err)

  def eval(text: String): Either[ForthError, ForthEvaluatorState] =
    val tokens = text.trim.toLowerCase.split("\\s+").filter(_.nonEmpty).toList
    loop(tokens, State())
end Forth
