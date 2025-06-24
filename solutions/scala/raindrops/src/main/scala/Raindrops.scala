import scala.collection.SortedMap

object Raindrops {
  private val drops = SortedMap(
    3 -> "Pling",
    5 -> "Plang",
    7 -> "Plong"
  )

  def convert(n: Int): String =
    val sounds = for {
      (factor, sound) <- drops if n % factor == 0
    } yield sound

    if sounds.isEmpty then n.toString else sounds.mkString
}

