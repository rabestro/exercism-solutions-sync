object BeerSong:
  private def verse(bottles: Int) = bottles match
    case 0 => List(
      "No more bottles of beer on the wall, no more bottles of beer.",
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n")
    case 1 => List(
      "1 bottle of beer on the wall, 1 bottle of beer.",
      "Take it down and pass it around, no more bottles of beer on the wall.\n")
    case 2 => List(
      "2 bottles of beer on the wall, 2 bottles of beer.",
      "Take one down and pass it around, 1 bottle of beer on the wall.\n")
    case i => List(
      s"$i bottles of beer on the wall, $i bottles of beer.",
      s"Take one down and pass it around, ${i - 1} bottles of beer on the wall.\n")

  def recite(startBottles: Int, takeDown: Int): String =
    startBottles.until(startBottles - takeDown, -1)
      .flatMap(verse)
      .mkString("\n")
