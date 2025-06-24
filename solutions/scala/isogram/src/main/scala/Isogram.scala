object Isogram:
  private val RepeatingLetter = raw"(?i)(\p{L}).*?\1".r

  def isIsogram(phrase: String): Boolean =
    (RepeatingLetter findFirstIn phrase).isEmpty
