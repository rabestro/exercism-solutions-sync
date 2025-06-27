class Matrix(data: String):
  private lazy val matrix = data.linesIterator.map(_.split(' ').map(_.toInt)).toSeq

  def row(i: Int): Seq[Int] = matrix(i)

  def column(i: Int): Seq[Int] = matrix.transpose.toSeq(i)