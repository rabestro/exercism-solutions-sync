object RnaTranscription {
  private val DnaRnaMap = Map(
    'C' -> 'G', 'G' -> 'C', 'T' -> 'A', 'A' -> 'U'
  )

  def toRna(dna: String): Some[String] =
    Some(dna.map(DnaRnaMap))
}