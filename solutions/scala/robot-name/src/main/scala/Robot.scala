class Robot:
  private var currentName: String = Robot.generateName()

  def name: String = currentName

  def reset(): Unit =
    currentName = Robot.generateName()


object Robot:
  private var id = 0

  private def generateName(): String =
    id += 1
    val number = id % 1000
    val prefix = id / 1000
    val first = (prefix / 26 + 'A').toChar
    val second = (prefix % 26 + 'A').toChar
    f"$first$second$number%03d"
