case class Garden(cups: List[List[Plant]]):
  private def order(name: String) = name.charAt(0) - 'A'

  def plants(name: String): List[Plant] = 
    cups.drop(order(name)).headOption.getOrElse(Nil)

object Garden:
  def defaultGarden(cups: String): Garden =
    val garden = cups.linesIterator.toList
      .map(_.sliding(2, 2).toList)
      .transpose
      .map(_.flatten)
      .map(_.flatMap(Plant.apply))
    Garden(garden)
