object Queen {
  def create(x: Int, y: Int): Option[Queen] =
    if x < 0 || x > 7 || y < 0 || y > 7 then None 
    else Some(Queen(x, y))
}

case class Queen(row: Int, col: Int) {

}