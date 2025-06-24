object Raindrops {
  def convert(n: Int): String =
    var sound = ""
    if n % 3 == 0 then sound += "Pling" 
    if n % 5 == 0 then sound += "Plang"
    if n % 7 == 0 then sound += "Plong"
    if sound.isEmpty then n.toString else sound
}