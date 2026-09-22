import scala.annotation.tailrec

object MatchingBrackets:
  private val BracketPairs = Map(
    ']' -> '[', ')' -> '(', '}' -> '{'
  )
  private val OpeningBrackets = BracketPairs.values.toSet
  private val ClosingBrackets = BracketPairs.keySet

  @tailrec
  def isPaired(brackets: String, stack: List[Char] = List()): Boolean =
    if brackets.isEmpty then stack.isEmpty
    else if ClosingBrackets contains brackets.head
    then stack match
        case openingBracket :: remainingStack
          if BracketPairs(brackets.head) == openingBracket =>
          isPaired(brackets.tail, remainingStack)
        case _ => false
    else if OpeningBrackets contains brackets.head
    then isPaired(brackets.tail, brackets.head :: stack)
    else isPaired(brackets.tail, stack)
