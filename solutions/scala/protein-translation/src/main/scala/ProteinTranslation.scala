object ProteinTranslation:
  private val Stop = "STOP"
  private type RnaSequence = String
  private type Protein = String
  private type Codon = String

  private def codonToProtein(codon: Codon): Protein =
    codon match
      case "AUG" => "Methionine"
      case "UUU" | "UUC" => "Phenylalanine"
      case "UUA" | "UUG" => "Leucine"
      case "UCU" | "UCC" | "UCA" | "UCG" => "Serine"
      case "UAU" | "UAC" => "Tyrosine"
      case "UGU" | "UGC" => "Cysteine"
      case "UGG" => "Tryptophan"
      case _ => Stop


  def proteins(rna: RnaSequence): Seq[Protein] =
    rna.sliding(3, 3).map(codonToProtein).takeWhile(_ != Stop).toSeq
