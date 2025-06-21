import Schedule.Schedule

import java.time.temporal.TemporalAdjusters.{dayOfWeekInMonth, firstInMonth, lastInMonth, nextOrSame}
import java.time.{DayOfWeek, LocalDate, Year}

case class Meetup(month: Int, year: Int) {
  private val firstTeenthDay = 13

  def day(day: Int, schedule: Schedule): LocalDate =
    val dayOfWeek = DayOfWeek.of(day)
    val adjuster = schedule match {
      case Schedule.First => firstInMonth(dayOfWeek)
      case Schedule.Second => dayOfWeekInMonth(2, dayOfWeek)
      case Schedule.Third => dayOfWeekInMonth(3, dayOfWeek)
      case Schedule.Fourth => dayOfWeekInMonth(4, dayOfWeek)
      case Schedule.Teenth => nextOrSame(dayOfWeek)
      case Schedule.Last => lastInMonth(dayOfWeek)
    }
    Year.of(year).atMonth(month).atDay(firstTeenthDay) `with` adjuster
}

object Schedule extends Enumeration {
  type Schedule = Value
  val Teenth, First, Second, Third, Fourth, Last = Value
}

object Meetup {
  val Mon = DayOfWeek.MONDAY.getValue
  val Tue = DayOfWeek.TUESDAY.getValue
  val Wed = DayOfWeek.WEDNESDAY.getValue
  val Thu = DayOfWeek.THURSDAY.getValue
  val Fri = DayOfWeek.FRIDAY.getValue
  val Sat = DayOfWeek.SATURDAY.getValue
  val Sun = DayOfWeek.SUNDAY.getValue
}
