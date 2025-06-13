import scala.annotation.tailrec

object MatchingBrackets {
  private val BracketPairs = Map(
    ']' -> '[', ')' -> '(', '}' -> '{'
  )
  private val OpeningBrackets = BracketPairs.values.toSet
  private val ClosingBrackets = BracketPairs.keySet

  def isPaired(brackets: String): Boolean =
    @tailrec
    def iter(brackets: List[Char], stack: List[Char]): Boolean =
      brackets match
        case head :: remainingBrackets
          if ClosingBrackets contains head =>
          stack match
            case openingBracket :: remainingStack
              if BracketPairs(head) == openingBracket =>
              iter(remainingBrackets, remainingStack)
            case _ => false
        case head :: remainingBrackets
          if OpeningBrackets contains head =>
          iter(remainingBrackets, head :: stack)
        case _ :: remainingBrackets => iter(remainingBrackets, stack)
        case Nil => stack.isEmpty

    iter(brackets.toList, List())
}
