import Bearing.*

case class Robot(bearing: Bearing, coordinates: (Int, Int)) {
  def turnRight: Robot = bearing match
    case North => copy(bearing = East)
    case East => copy(bearing = South)
    case South => copy(bearing = West)
    case West => copy(bearing = North)

  def turnLeft: Robot = bearing match
    case North => copy(bearing = West)
    case East => copy(bearing = North)
    case South => copy(bearing = East)
    case West => copy(bearing = South)

  def advance: Robot = bearing match
    case North => copy(coordinates = (coordinates._1, coordinates._2 + 1))
    case East => copy(coordinates = (coordinates._1 + 1, coordinates._2))
    case South => copy(coordinates = (coordinates._1, coordinates._2 - 1))
    case West => copy(coordinates = (coordinates._1 - 1, coordinates._2))

  def simulate(actions: String): Robot =
    actions.foldLeft(this){ (robot, action) =>
      action match
        case 'A' => robot.advance
        case 'R' => robot.turnRight
        case 'L' => robot.turnLeft
    }
}
