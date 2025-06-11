class Lasagna {
  def expectedMinutesInOven() = 40

  def remainingMinutesInOven(minutes: Int): Int = expectedMinutesInOven() - minutes

  def preparationTimeInMinutes(layer: Int): Int = layer * 2

  def elapsedTimeInMinutes(layer: Int, minutes: Int): Int = preparationTimeInMinutes(layer) + minutes
}
