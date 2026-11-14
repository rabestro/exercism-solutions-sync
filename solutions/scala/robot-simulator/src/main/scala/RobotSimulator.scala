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

end Bearing

case class Robot(bearing: Bearing, coordinates: (Int, Int)):
  private def turnRight: Robot = copy(bearing = bearing.turnRight)

  private def turnLeft: Robot = copy(bearing = bearing.turnLeft)

  private def advance: Robot =
    val (x, y) = coordinates
    val nextPosition = bearing match
      case Bearing.North => (x, y + 1)
      case Bearing.East => (x + 1, y)
      case Bearing.South => (x, y - 1)
      case Bearing.West => (x - 1, y)
    copy(coordinates = nextPosition)

  def simulate(actions: String): Robot =
    actions.foldLeft(this): (robot, action) =>
      action match
        case 'A' => robot.advance
        case 'R' => robot.turnRight
        case 'L' => robot.turnLeft

end Robot
