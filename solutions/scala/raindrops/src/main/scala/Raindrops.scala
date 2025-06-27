object Raindrops {
  private val drops = Seq(
    3 -> "Pling",
    5 -> "Plang",
    7 -> "Plong"
  )

  def convert(number: Int): String =
    val sounds = for {
      (factor, sound) <- drops if number % factor == 0
    } yield sound

    if sounds.isEmpty then number.toString else sounds.mkString
}
