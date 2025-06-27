enum Plant:
  case Grass, Clover, Radishes, Violets

object Plant:
  def apply(plantSymbol: Char): Option[Plant] = plantSymbol match
    case 'G' => Some(Plant.Grass)
    case 'C' => Some(Plant.Clover)
    case 'R' => Some(Plant.Radishes)
    case 'V' => Some(Plant.Violets)
    case _ => None
