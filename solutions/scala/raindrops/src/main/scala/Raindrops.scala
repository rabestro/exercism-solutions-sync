object Raindrops {
  def convert(n: Int): String =
    val sound = Seq(
      3 -> "Pling", 
      5 -> "Plang", 
      7 -> "Plong"
    ).filter(n % _._1 == 0).map(_._2)

    if sound.isEmpty then n.toString else sound.mkString
}

