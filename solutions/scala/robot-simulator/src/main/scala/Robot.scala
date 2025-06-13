import Bearing.*

case class Robot(bearing: Bearing, coordinates: (Int, Int)) {
  def turnRight: Robot = copy(bearing = bearing.turnRight)

  def turnLeft: Robot = copy(bearing = bearing.turnLeft)

  def advance: Robot =
    val (x, y) = coordinates
    val nextPosition = bearing match
      case Bearing.North => (x, y + 1)
      case Bearing.East => (x + 1, y)
      case Bearing.South => (x, y - 1)
      case Bearing.West => (x - 1, y)
    copy(coordinates = nextPosition)

  def simulate(actions: String): Robot =
    actions.foldLeft(this) { (robot, action) =>
      action match
        case 'A' => robot.advance
        case 'R' => robot.turnRight
        case 'L' => robot.turnLeft
    }
}
