object PascalsTriangle:
  def rows(count: Int): Seq[Seq[Int]] =
    Seq.iterate(Seq(1), count) {
      row => 0 +: row zip row :+ 0 map { case (a, b) => a + b }
    }
