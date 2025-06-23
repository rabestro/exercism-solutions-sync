object RnaTranscription {
  private val DnaRnaMap = Map(
    'C' -> 'G', 'G' -> 'C', 'T' -> 'A', 'A' -> 'U'
  )

  def toRna(dna: String): Option[String] = {
    val result = dna.foldLeft(Option(new StringBuilder)) {
      case (Some(builder), base) => DnaRnaMap.get(base).map(builder.append)
      case _ => None // If any base is invalid, propagate None
    }
    result.map(_.toString())
  }
}
