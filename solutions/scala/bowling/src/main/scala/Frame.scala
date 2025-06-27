sealed trait Frame

sealed trait StateFrame extends Frame

case object EmptyFrame extends StateFrame

case class IncompleteFrame(first: Int) extends StateFrame

sealed trait CompletedFrame extends Frame

case class OpenFrame(first: Int, second: Int) extends CompletedFrame

case class SpareFrame(first: Int, second: Int) extends CompletedFrame

case object StrikeFrame extends CompletedFrame

