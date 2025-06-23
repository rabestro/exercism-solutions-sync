import scala.math.abs

object QueenAttack:
  def canAttack(whiteQueen: Queen, blackQueen: Queen): Boolean =
    whiteQueen canAttack blackQueen

object Queen:
  private def isValidPosition(x: Int, y: Int) =
    x >= 0 && x <= 7 && y >= 0 && y <= 7

  def create(x: Int, y: Int): Option[Queen] =
    if isValidPosition(x, y) then Some(Queen(x, y)) else None

case class Queen(row: Int, col: Int):
  infix def canAttack(that: Queen): Boolean =
    this.row == that.row || this.col == that.col ||
      abs(this.col - that.col) == abs(this.row - that.row)
