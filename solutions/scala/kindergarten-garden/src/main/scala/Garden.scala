enum Plant:
  case Grass, Clover, Radishes, Violets

case class Garden(var plants: List[List[Plant]]):
  def plants(child: String): List[Plant] =
    val index = child.head - 'A'
    plants(index)

object Garden:
  private def charToPlant(c: Char): Option[Plant] = c match
    case 'G' => Some(Plant.Grass)
    case 'C' => Some(Plant.Clover)
    case 'R' => Some(Plant.Radishes)
    case 'V' => Some(Plant.Violets)
    case _ => None

  def defaultGarden(diagram: String): Garden =
    val lines = diagram.split('\n').map(_.grouped(2))
    val garden = lines.head.zip(lines.tail.head)
      .map(_ + _)
      .map(_.flatMap(charToPlant))
      .map(_.toList)
      .toList
    Garden(garden)

