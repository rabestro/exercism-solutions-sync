final case class DNA(strand: String):
  private val validNucleotides = "ACGT".toSet

  def nucleotideCounts: Either[String, Map[Char, Int]] =
    if strand forall validNucleotides.contains then
      val counts = strand.groupBy(identity).view.mapValues(_.length).toMap
      Right(validNucleotides.map(n => n -> counts.getOrElse(n, 0)).toMap)
    else
      Left("Invalid nucleotide found")