object House {
  private val Subject = List(
    "house",
    "malt",
    "rat",
    "cat",
    "dog",
    "cow with the crumpled horn",
    "maiden all forlorn",
    "man all tattered and torn",
    "priest all shaven and shorn",
    "rooster that crowed in the morn",
    "farmer sowing his corn",
    "horse and the hound and the horn"
  )

  private val Verb = List(
    "lay in",
    "ate",
    "killed",
    "worried",
    "tossed",
    "milked",
    "kissed",
    "married",
    "woke",
    "kept",
    "belonged to"
  )

  private def phrase(verses: Int): String =
    if verses == 0 then s"the ${Subject(verses)}"
    else s"the ${Subject(verses)} that ${Verb(verses - 1)} ${phrase(verses - 1)}"

  private def nurseryRhyme(verses: Int): String =
    s"This is ${phrase(verses - 1)} that Jack built.\n"

  def recite(a: Int, b: Int): String =
    (a to b).map(nurseryRhyme).toList.mkString("", "", "\n")
}