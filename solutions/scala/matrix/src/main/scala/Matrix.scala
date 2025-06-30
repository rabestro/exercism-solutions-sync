class Matrix(data: String):

  lazy val row: Seq[Array[Int]] = data.linesIterator.map(_.split(' ').map(_.toInt)).toSeq

  lazy val column: Seq[Seq[Int]] = row.transpose