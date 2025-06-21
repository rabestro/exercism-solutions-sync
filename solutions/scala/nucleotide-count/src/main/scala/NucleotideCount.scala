final case class DNA(strand: String):
  private val validNucleotides = "ACGT"

  def nucleotideCounts: Either[String, Map[Char, Int]] =
    if strand forall (validNucleotides contains _) then
      Right(
        validNucleotides.map(_ -> 0).toMap ++
          strand.groupBy(identity).view.mapValues(_.length).toMap
      )
    else
      Left("Invalid nucleotide found")
