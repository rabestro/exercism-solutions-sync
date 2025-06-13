enum Bearing:
  case North, South, East, West

  def turnRight: Bearing = this match
    case North => East
    case East => South
    case South => West
    case West => North

  def turnLeft: Bearing = this match
    case North => West
    case West => South
    case South => East
    case East => North