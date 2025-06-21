import scala.annotation.targetName

case class Clock private (minutes: Int) {
  @targetName("add")
  def +(other: Clock): Clock =
    Clock(minutes + other.minutes)

  @targetName("minus")
  def -(other: Clock): Clock =
    Clock(minutes - other.minutes)
}

object Clock {
  private val MinutesInHour = 60
  private val MinutesInDay = MinutesInHour * 24

  def apply(hours: Int, minutes: Int): Clock =
    val totalMinutes = hours * MinutesInHour + minutes
    val normalizedMinutes = (totalMinutes % MinutesInDay + MinutesInDay) % MinutesInDay
    new Clock(normalizedMinutes)

  def apply(minutes: Int): Clock =
    Clock(0, minutes)
}