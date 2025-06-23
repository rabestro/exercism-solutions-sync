import scala.math.abs

object QueenAttack {
  def canAttack(white: Queen, black: Queen): Boolean =
    def sameRow = white.row == black.row

    def sameCol = white.col == black.col

    def sameDiagonal = abs(white.col - black.col) == abs(white.row - black.row)

    sameRow || sameCol || sameDiagonal
}
