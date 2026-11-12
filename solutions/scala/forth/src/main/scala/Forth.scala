import ForthError.ForthError

import scala.annotation.tailrec


type Stack = List[Int]
type Dictionary = Map[String, Word]

/** A word resolved against the dictionary that existed when it was defined. */
enum Word:
  case Num(n: Int)
  case Builtin(run: Stack => Either[ForthError, Stack])
  case User(body: List[Word])


case class State(stack: Stack = Nil) extends ForthEvaluatorState:
  override def toString: String = stack.reverse.mkString(" ")


object Builtins:
  private def binary(op: (Int, Int) => Int): Stack => Either[ForthError, Stack] =
    case a :: b :: tail => Right(op(b, a) :: tail)
    case _              => Left(ForthError.StackUnderflow)

  private val div: Stack => Either[ForthError, Stack] =
    case 0 :: _ :: _ => Left(ForthError.DivisionByZero)
    case stack       => binary(_ / _)(stack)

  private val dup: Stack => Either[ForthError, Stack] =
    case a :: tail => Right(a :: a :: tail)
    case _         => Left(ForthError.StackUnderflow)

  private val drop: Stack => Either[ForthError, Stack] =
    case _ :: tail => Right(tail)
    case _         => Left(ForthError.StackUnderflow)

  private val swap: Stack => Either[ForthError, Stack] =
    case a :: b :: tail => Right(b :: a :: tail)
    case _              => Left(ForthError.StackUnderflow)

  private val over: Stack => Either[ForthError, Stack] =
    case a :: b :: tail => Right(b :: a :: b :: tail)
    case _              => Left(ForthError.StackUnderflow)

  val dictionary: Dictionary = Map(
    "+"    -> binary(_ + _),
    "-"    -> binary(_ - _),
    "*"    -> binary(_ * _),
    "/"    -> div,
    "dup"  -> dup,
    "drop" -> drop,
    "swap" -> swap,
    "over" -> over
  ).view.mapValues(Word.Builtin(_)).toMap
end Builtins


class Forth extends ForthEvaluator:
  private case class Definition(name: String, body: List[String])

  private def parseNumber(token: String): Option[Int] =
    if token.matches("-?\\d+") then token.toIntOption else None

  /** Parses the tokens following ":" into a definition and the tokens after ";". */
  private def parseDefinition(tokens: List[String]): Either[ForthError, (Definition, List[String])] =
    tokens.span(_ != ";") match
      case (name :: body, ";" :: rest) if parseNumber(name).isEmpty =>
        Right((Definition(name, body), rest))
      case _ =>
        Left(ForthError.InvalidWord)

  private def resolve(token: String, dictionary: Dictionary): Either[ForthError, Word] =
    dictionary.get(token)
      .orElse(parseNumber(token).map(Word.Num(_)))
      .toRight(ForthError.UnknownWord)

  private def resolveAll(tokens: List[String], dictionary: Dictionary): Either[ForthError, List[Word]] =
    tokens.foldRight[Either[ForthError, List[Word]]](Right(Nil)): (token, acc) =>
      for
        words <- acc
        word  <- resolve(token, dictionary)
      yield word :: words

  @tailrec
  private def run(words: List[Word], stack: Stack): Either[ForthError, Stack] =
    words match
      case Nil                    => Right(stack)
      case Word.Num(n) :: rest    => run(rest, n :: stack)
      case Word.User(body) :: rest => run(body ++ rest, stack)
      case Word.Builtin(op) :: rest =>
        op(stack) match
          case Right(next) => run(rest, next)
          case Left(err)   => Left(err)

  @tailrec
  private def loop(tokens: List[String], stack: Stack, dictionary: Dictionary): Either[ForthError, Stack] =
    tokens match
      case Nil => Right(stack)

      case ":" :: rest =>
        val defined =
          for
            (definition, remaining) <- parseDefinition(rest)
            body                    <- resolveAll(definition.body, dictionary)
          yield (remaining, dictionary + (definition.name -> Word.User(body)))

        defined match
          case Right((remaining, updated)) => loop(remaining, stack, updated)
          case Left(err)                   => Left(err)

      case token :: rest =>
        resolve(token, dictionary).flatMap(word => run(List(word), stack)) match
          case Right(next) => loop(rest, next, dictionary)
          case Left(err)   => Left(err)

  def eval(text: String): Either[ForthError, ForthEvaluatorState] =
    val tokens = text.toLowerCase.split("\\s+").filter(_.nonEmpty).toList
    loop(tokens, Nil, Builtins.dictionary).map(State(_))
end Forth
