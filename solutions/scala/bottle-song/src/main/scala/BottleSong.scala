object BottleSong:
  private val numbers = List("no", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")

  private def suffix(bottles: Int) = if bottles == 1 then "" else "s"

  private def hanging(bottles: Int) =
    s"${numbers(bottles)} green bottle${suffix(bottles)} hanging on the wall"

  private def verse(bottles: Int) =
    Seq(
      hanging(bottles) + ",",
      hanging(bottles) + ",",
      "And if one green bottle should accidentally fall,",
      "There'll be " + hanging(bottles - 1).toLowerCase
    ).mkString("", "\n", ".\n")


  def recite(startBottles: Int, takeDown: Int): String =
    startBottles until startBottles - takeDown by -1 map verse mkString "\n"