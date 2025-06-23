import scala.math.abs

object Queen {
  def create(x: Int, y: Int): Option[Queen] =
    if x < 0 || x > 7 || y < 0 || y > 7 then None
    else Some(Queen(x, y))
}

final case class Queen(row: Int, col: Int) {

}

object QueenAttack {
  def canAttack(white: Queen, black: Queen): Boolean =
    def sameRow = white.row == black.row

    def sameCol = white.col == black.col

    def sameDiagonal = abs(white.col - black.col) == abs(white.row - black.row)

    sameRow || sameCol || sameDiagonal
}
