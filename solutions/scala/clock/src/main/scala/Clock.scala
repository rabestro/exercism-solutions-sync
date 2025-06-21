import scala.annotation.targetName

case class Clock(hours: Int, minutes: Int) {
  this(minutes: Int) = this(0, minutes)

  @targetName("+")
  infix def +(other: Clock): Clock = ???
}