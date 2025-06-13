object House:
  private val verseLines = List(
    "the house that Jack built",
    "the malt that lay in",
    "the rat that ate",
    "the cat that killed",
    "the dog that worried",
    "the cow with the crumpled horn that tossed",
    "the maiden all forlorn that milked",
    "the man all tattered and torn that kissed",
    "the priest all shaven and shorn that married",
    "the rooster that crowed in the morn that woke",
    "the farmer sowing his corn that kept",
    "the horse and the hound and the horn that belonged to"
  )

  private def verse(length: Int) =
    (0 to length).reverse.map(verseLines).mkString("This is ", " ", ".")

  def recite(start: Int, end: Int): String =
    (start - 1 until end).map(verse).mkString("", "\n", "\n\n")
