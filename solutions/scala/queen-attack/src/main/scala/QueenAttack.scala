import scala.math.abs

object Queen {
  private def isValidPosition(x: Int, y: Int): Boolean =
    x >= 0 && x <= 7 && y >= 0 && y <= 7

  def create(x: Int, y: Int): Option[Queen] =
    if isValidPosition(x, y) then Some(Queen(x, y)) else None
}

final case class Queen(row: Int, col: Int) {
  def canAttack(other: Queen): Boolean =
    this.row == other.row || this.col == other.col ||
      abs(this.col - other.col) == abs(this.row - other.row)
}

object QueenAttack {
  def canAttack(white: Queen, black: Queen): Boolean = white.canAttack(black)
}
