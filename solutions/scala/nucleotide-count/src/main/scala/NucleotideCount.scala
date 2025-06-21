final case class DNA(strand: String):
  private val validNucleotides = "ACGT".toSet

  def nucleotideCounts: Either[String, Map[Char, Int]] =
    if strand forall validNucleotides.contains then
      Right(validNucleotides.map(nucleotide => nucleotide -> strand.count(_ == nucleotide)).toMap)
    else
      Left("Invalid nucleotide found")